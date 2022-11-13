package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.dto.CommentDto;
import com.binbini.imall.exception.IMallException;
import com.binbini.imall.mapper.TbCommentMapper;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.mapper.TbLikeContentMapper;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbLikeContent;
import com.binbini.imall.service.CommentService;
import com.binbini.imall.utils.IdGen;
import com.binbini.imall.utils.RedisUtil;
import com.binbini.imall.vo.CommentVo;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.MessageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/08:35
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TbCommentMapper tbCommentMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public int createComment(CommentDto commentDto) {
        TbComment tbComment = new TbComment();
        if (commentDto.getUser_id().equals("") || commentDto.getProduct_id().equals("") ||
        commentDto.getScore() <= 0 || commentDto.getScore() > 5 || commentDto.getContent().equals("") ||
        commentDto.getIs_parent().equals("")) {
            return 0;
        }
        if (commentDto.getIs_parent() == 0 && commentDto.getParent_id().equals("")) {
            return 0;
        }
        tbComment.setId(IdGen.randomInteger())
                .setUser_id(commentDto.getUser_id())
                .setProduct_id(commentDto.getProduct_id())
                .setProduct_version(commentDto.getProduct_version())
                .setProduct_color(commentDto.getProduct_color())
                .setScore(commentDto.getScore())
                .setContent(commentDto.getContent())
                .setIs_parent(commentDto.getIs_parent())
                .setParent_id(commentDto.getParent_id())
                .setCreated(new Date());
        if (tbCommentMapper.insert(tbComment) != 1) {
            return -1;
        }
        MessageVo<Integer> messageVo = new MessageVo<>();
        messageVo.setTitle("add_comment");
        messageVo.setData(tbComment.getId());
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.comment", messageVo);
        return 1;
    }

    @Override
    public DataTablesResult findCommentSearchPage(int start, int length, Integer productId, String orderByCol, String orderBySort) {
        DataTablesResult result = new DataTablesResult();

        PageHelper.startPage(start, length);
        QueryWrapper<TbComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("is_parent", 1);
        if (orderBySort.equals("asc")) {
            queryWrapper.orderByAsc(orderByCol);
        }
        if (orderBySort.equals("desc")) {
            queryWrapper.orderByDesc(orderByCol);
        }
        List<TbComment> tbComments = tbCommentMapper.selectList(queryWrapper);
        TbItem tbItem = tbItemMapper.selectById(productId);
        String[] versions = tbItem.getVersion().split(",");
        String[] colors = tbItem.getColor().split(",");

        List<CommentVo> commentVos = new ArrayList<>();
        for (TbComment tbComment:tbComments) {
            CommentVo commentVo = new CommentVo();
            commentVo.setId(tbComment.getId())
                    .setUser_id(tbComment.getUser_id())
                    .setProduct_id(tbComment.getProduct_id())
                    .setProduct_version(versions[tbComment.getProduct_version()])
                    .setProduct_color(colors[tbComment.getProduct_color()])
                    .setScore(tbComment.getScore())
                    .setContent(tbComment.getContent())
                    .setIs_parent(tbComment.getIs_parent())
                    .setParent_id(tbComment.getParent_id())
                    .setCreated(tbComment.getCreated());
            commentVos.add(commentVo);
        }

        PageInfo<CommentVo> pageInfo = new PageInfo<>(commentVos);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsFiltered((int)pageInfo.getTotal());

        result.setSuccess(true);
        result.setData(commentVos);
        return result;
    }

    @Override
    public TbComment findCommentById(Integer id) {
        TbComment tbComment = tbCommentMapper.selectById(id);
        if (tbComment == null) {
            throw new IMallException("No corresponding information");
        }
        return tbComment;
    }

    @Override
    public List<TbComment> findCommentByParentId(Integer parent_id) {
        if (parent_id.equals("")) {
            return null;
        }
        QueryWrapper<TbComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parent_id);
        List<TbComment> list = tbCommentMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public boolean del(Integer id) {
        return tbCommentMapper.deleteById(id) == 1;
    }
}
