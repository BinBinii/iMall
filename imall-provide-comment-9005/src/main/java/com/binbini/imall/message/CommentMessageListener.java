package com.binbini.imall.message;

import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.mapper.TbCommentMapper;
import com.binbini.imall.mapper.TbLikeContentMapper;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.pojo.TbLikeContent;
import com.binbini.imall.vo.MessageVo;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/17:05
 * @Description:
 */
@Component
public class CommentMessageListener {

    @Autowired
    private TbLikeContentMapper tbLikeContentMapper;
    @Autowired
    private TbCommentMapper tbCommentMapper;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_COMMENT})
    public void comment_message(Object msg, Message message, Channel channel) throws IOException {
        MessageVo<Integer> messageVo = new Gson().fromJson(new String(message.getBody()), MessageVo.class);
        String messageTitle = messageVo.getTitle();
        if ("add_comment".equals(messageTitle)) {
            TbComment tbComment = tbCommentMapper.selectById(messageVo.getData());
            TbLikeContent tbLikeContent = new TbLikeContent();
            tbLikeContent.setComment_id(tbComment.getId())
                    .setLike_number(0)
                    .setComment_number(0)
                    .setCreate_user(tbComment.getUser_id())
                    .setCreated(tbComment.getCreated())
                    .setUpdated(tbComment.getCreated());
            if (tbLikeContentMapper.insert(tbLikeContent) != 1) {
                // TODO 重新进入队列
            }
        }
    }

}
