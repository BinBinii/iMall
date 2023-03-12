package com.binbini.imall.message;

import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.dto.OrderItemDto;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.mapper.TbOrderItemMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbOrderItem;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: BinBin
 * @Date: 2022/11/19/15:22
 * @Description:
 */
@Component
public class OrderItemMessageListener {
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbItemMapper tbItemMapper;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_ORDER_ITEM})
    public void order_item_message(Object msg, Message message, Channel channel) {
        OrderDto orderDto = new Gson().fromJson(new String(message.getBody()), OrderDto.class);
        for (OrderItemDto orderItemDto: orderDto.getOrderItemDtoList()) {
            TbItem tbItem = tbItemMapper.selectById(orderItemDto.getItem_id());
            String[] prices = tbItem.getPrice().split(",");
            String[] versions = tbItem.getVersion().split(",");
            String[] colors = tbItem.getColor().split(",");
            String[] images = tbItem.getImage().split(",");
            TbOrderItem tbOrderItem = new TbOrderItem();
            String itemInfo = versions[orderItemDto.getVersion()] + " " +colors[orderItemDto.getColor()];
            tbOrderItem.setItem_id(orderItemDto.getItem_id())
                    .setOrder_number(orderDto.getOrder_number())
                    .setVersion(orderItemDto.getVersion())
                    .setColor(orderItemDto.getColor())
                    .setItem_info(itemInfo)
                    .setNum(orderItemDto.getNum())
                    .setTitle(tbItem.getTitle())
                    .setPrice(Double.parseDouble(prices[orderItemDto.getVersion()]))
                    .setTotal_fee(Double.parseDouble(prices[orderItemDto.getVersion()]) * orderItemDto.getNum())
                    .setPic_path(images[0]);
            if (tbOrderItemMapper.insert(tbOrderItem) != 1) {
                // TODO 回滚
            }
        }
    }
}
