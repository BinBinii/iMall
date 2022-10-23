package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.exception.IMallException;
import com.binbini.imall.mapper.TbOrderMapper;
import com.binbini.imall.pojo.TbOrder;
import com.binbini.imall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public TbOrder createOrder(OrderDto orderDto) {
        TbOrder tbOrder = new TbOrder();
        if (orderDto.getOrder_number().equals("") ||
            orderDto.getPayment() <= 0 ||
            orderDto.getPost_fee() < 0 ||
            orderDto.getUser_id() < 0 ||
            orderDto.getBuyer_nick().equals("")) {
            return null;
        }
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
                .setBuyer_comment(0);
        if (tbOrderMapper.insert(tbOrder) != 1) {
            // TODO 订单进入Redis缓存，在用户待支付界面显示（生命周期为30分钟）
            return null;
        }
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
        tbOrder.setStatus(1)
                .setUpdate_time(new Date())
                .setPayment_time(new Date());
        if (tbOrderMapper.updateById(tbOrder) != 1) {
            // TODO 进入mq队列，重新执行更新操作
            return -2;
        }
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
}
