package com.binbini.imall.message;

import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.vo.MessageVo;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_ITEM})
    public void item_message(Object msg, Message message, Channel channel) throws IOException {
        MessageVo<Integer> messageVo = new Gson().fromJson(new String(message.getBody()), MessageVo.class);
        String messageTitle = messageVo.getTitle();
        if ("add_item".equals(messageTitle)) {
            TbItem tbItem = tbItemMapper.selectById(messageVo.getData());

            IndexRequest request = new IndexRequest(ITEM_INDEX);
            request.timeout("3s");
            request.source(new Gson().toJson(tbItem), XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
        }
        if ("del_item".equals(messageTitle)) {
//            DeleteRequest request = new DeleteRequest(ITEM_INDEX, );
//            request.timeout("3s");
//            client.delete(request, RequestOptions.DEFAULT);
        }
    }

}
