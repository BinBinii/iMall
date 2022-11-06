package com.binbini.imall.message;

import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.vo.MessageVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: BinBin
 * @Date: 2022/10/29/13:33
 * @Description:
 */
@Component
public class ItemESMessageListener {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Value("${item.index}")
    private String ITEM_INDEX;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_ITEM})
    public void item_message(Object msg, Message message, Channel channel) {
        MessageVo<Integer> messageVo = new ObjectMapper().convertValue(msg, MessageVo.class);
        String messageTitle = messageVo.getTitle();
        if ("add_item".equals(messageTitle)) {
            TbItem tbItem = tbItemMapper.selectById(messageVo.getData());

            IndexRequest request = new IndexRequest(ITEM_INDEX);
            request.timeout("3s");
            request.source(new Gson().toJson(tbItem), XContentType.JSON);
        }
    }

}
