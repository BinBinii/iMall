package com.binbini.imall.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: BinBin
 * @Date: 2022/10/29/13:43
 * @Description:
 */
@Configuration
public class RabbitmqConfig {
    public static final String QUEUE_INFORM_ORDER_ITEM = "queue_inform_order_item";
    public static final String QUEUE_INFORM_PAY_ORDER = "queue_inform_pay_order";
    public static final String QUEUE_INFORM_DEL_CART = "queue_inform_del_cart";
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
    public static final String ROUTINGKEY_ORDER_ITEM = "inform.#.order_item.#";
    public static final String ROUTINGKEY_PAY_ORDER = "inform.#.pay_order.#";
    public static final String ROUTINGKEY_DEL_CART = "inform.#.del_cart.#";

    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    // 声明QUEUE_INFORM_ITEM队列
    @Bean(QUEUE_INFORM_ORDER_ITEM)
    public Queue QUEUE_INFORM_ORDER_ITEM(){
        return new Queue(QUEUE_INFORM_ORDER_ITEM);
    }

    //ROUTINGKEY_ITEM队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_ROUTINGKEY_ORDER_ITEM(@Qualifier(QUEUE_INFORM_ORDER_ITEM) Queue queue,
                                           @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_ORDER_ITEM).noargs();
    }

    @Bean(QUEUE_INFORM_PAY_ORDER)
    public Queue QUEUE_INFORM_PAY() {
        return new Queue(QUEUE_INFORM_PAY_ORDER);
    }

    @Bean
    public Binding BINDING_ROUTINGKEY_PAY(@Qualifier(QUEUE_INFORM_PAY_ORDER) Queue queue,
                                          @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_PAY_ORDER).noargs();
    }

    @Bean(QUEUE_INFORM_DEL_CART)
    public Queue QUEUE_INFORM_DEL_CART() {
        return new Queue(QUEUE_INFORM_DEL_CART);
    }

    @Bean
    public Binding BINDING_ROUTINGKEY_DEL_CART(@Qualifier(QUEUE_INFORM_DEL_CART) Queue queue,
                                          @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_DEL_CART).noargs();
    }

    @Bean
    public MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
