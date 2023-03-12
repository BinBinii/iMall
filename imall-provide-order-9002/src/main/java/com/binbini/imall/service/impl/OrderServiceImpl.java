package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.dto.OrderItemDto;
import com.binbini.imall.exception.IMallException;
import com.binbini.imall.mapper.TbAddressMapper;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.mapper.TbOrderMapper;
import com.binbini.imall.model.IPayOrderVo;
import com.binbini.imall.pojo.TbAddress;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbOrder;
import com.binbini.imall.service.OrderService;
import com.binbini.imall.utils.IdGen;
import com.binbini.imall.utils.RedisUtil;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/18:13
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbAddressMapper tbAddressMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${order.pre")
    private String ORDER_PRE;

    @Override
    public TbOrder createOrder(OrderDto orderDto) {
        orderDto.setOrder_number(IdGen.uuid());
        double paymentResult = statisticsMoney(orderDto);
        orderDto.setPayment(paymentResult);
        TbOrder tbOrder = new TbOrder();
        if (orderDto.getOrder_number().equals("") ||
            orderDto.getPayment() <= 0 ||
            orderDto.getPost_fee() < 0 ||
            orderDto.getUser_id() < 0 ||
            orderDto.getBuyer_nick().equals("")) {
            return null;
        }
        TbAddress tbAddress = tbAddressMapper.selectById(orderDto.getAddress_id());
        String addressInfo = tbAddress.getUser_name() + "，" + tbAddress.getTel() + "，" + tbAddress.getStreet_name();
        tbOrder.setOrder_number(orderDto.getOrder_number())
                .setPayment(orderDto.getPayment())
                .setPayment_type(orderDto.getPayment_type())
                .setPost_fee(orderDto.getPost_fee())
                .setStatus(0)
                .setCreate_time(new Date())
                .setUpdate_time(new Date())
                .setUser_id(orderDto.getUser_id())
                .setBuyer_message(orderDto.getBuyer_message())
                .setBuyer_nick(orderDto.getBuyer_nick())
                .setBuyer_comment(0)
                .setAddress_info(addressInfo);

        if (tbOrderMapper.insert(tbOrder) != 1) {
            return null;
        }
        // TODO 订单进入Redis缓存，在用户待支付界面显示（生命周期为30分钟）
        redisUtil.set(ORDER_PRE + ":" + orderDto.getOrder_number() + "", new Gson().toJson(orderDto));
        redisUtil.expire(ORDER_PRE + ":" + orderDto.getOrder_number() + "", 1800);
        // 向QUEUE_INFORM_ORDER_ITEM消息队列发送消息，同步TbOrderItem表
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.order_item", orderDto);
        // 向QUEUE_INFORM_PAY队列发送消息，同步TbPay表
        IPayOrderVo iPayOrderVo = new IPayOrderVo();
        iPayOrderVo.setUser_id(orderDto.getUser_id())
                .setNumber(orderDto.getOrder_number())
                .setMerchant_name("iMall")
                .setCommodity_name("iMall产品")
                .setMoney(orderDto.getPayment());
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.pay_order", iPayOrderVo);
        // 向QUEUE_INFORM_DEL_CART队列发送消息，删除用户购物车内对应的商品
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.del_cart", orderDto);
        return tbOrder;
    }

    @Override
    public Integer pay(String payNumber) {
        if (payNumber.equals("")) {
            return 0;
        }
        QueryWrapper<TbOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", payNumber);
        TbOrder tbOrder = tbOrderMapper.selectOne(queryWrapper);
        if (tbOrder == null) {
            return -1;
        }
        String json = String.valueOf(redisUtil.get(ORDER_PRE + ":" + payNumber + ""));
        if (json == null) {
            tbOrder.setStatus(6)
                    .setUpdate_time(new Date())
                    .setClose_time(new Date());
            return 6;
        } else {
            tbOrder.setStatus(1)
                    .setUpdate_time(new Date())
                    .setPayment_time(new Date());
        }
        if (tbOrderMapper.updateById(tbOrder) != 1) {
            // TODO 进入mq队列，重新执行更新操作
            return -2;
        }
        redisUtil.deleteKey(ORDER_PRE + ":" + payNumber + "");
        return 1;
    }

    @Override
    public TbOrder findById(Integer id) {
        TbOrder tbOrder = tbOrderMapper.selectById(id);
        if (tbOrder == null) {
            throw new IMallException("No corresponding information");
        }
        return tbOrder;
    }

    @Override
    public Integer receipt(Integer orderId) {
        TbOrder tbOrder = tbOrderMapper.selectById(orderId);
        tbOrder.setEnd_time(new Date())
                .setUpdate_time(new Date())
                .setStatus(4);
        if (tbOrderMapper.updateById(tbOrder) != 1) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer comment(Integer orderId) {
        TbOrder tbOrder = tbOrderMapper.selectById(orderId);
        tbOrder.setUpdate_time(new Date())
                .setBuyer_comment(1);
        if (tbOrderMapper.updateById(tbOrder) != 1) {
            return 0;
        }
        return 1;
    }

    // 统计商品总金额
    private double statisticsMoney(OrderDto orderDto) {
        double result = 0;
        for (OrderItemDto orderItemDto: orderDto.getOrderItemDtoList()) {
            TbItem tbItem = tbItemMapper.selectById(orderItemDto.getItem_id());
            String[] prices = tbItem.getPrice().split(",");
            double price = Double.parseDouble(prices[orderItemDto.getVersion()]) * orderItemDto.getNum();
            result += price;
        }
        return result;
    }

}
