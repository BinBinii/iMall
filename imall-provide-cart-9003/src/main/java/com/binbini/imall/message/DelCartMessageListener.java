package com.binbini.imall.message;

import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.dto.OrderItemDto;
import com.binbini.imall.utils.RedisUtil;
import com.binbini.imall.vo.CartProduct;
import com.binbini.imall.vo.CartVo;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/22/10:47
 * @Description:
 */
@Component
public class DelCartMessageListener {
    @Autowired
    private RedisUtil redisUtil;

    @Value("${cart.pre}")
    private String CART_PRE;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_DEL_CART})
    public void del_cart_message(Object msg, Message message, Channel channel) {
        OrderDto orderDto = new Gson().fromJson(new String(message.getBody()), OrderDto.class);
        String json = String.valueOf(redisUtil.get(CART_PRE + ":" + orderDto.getUser_id() + ""));
        CartVo cartVo = new Gson().fromJson(json, CartVo.class);
        List<CartProduct> cartProductList = cartVo.getProductList();
        for (OrderItemDto orderItemDto:orderDto.getOrderItemDtoList()) {
            for (CartProduct cartProduct:cartProductList) {
                if (orderItemDto.getItem_id().equals(cartProduct.getItem_id()) && orderItemDto.getVersion().equals(cartProduct.getVersion()) && orderItemDto.getColor().equals(cartProduct.getColor())) {
                    cartProductList.remove(cartProduct);
                    break;
                }
            }
        }
        redisUtil.set(CART_PRE + ":" + orderDto.getUser_id() + "", new Gson().toJson(cartVo));
    }
}
