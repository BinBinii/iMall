package com.binbini.imall.service;

import com.binbini.imall.dto.CommentDto;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/08:34
 * @Description:
 */
@Service
public interface CommentService {

    /**
     * 发表评论
     * @param commentDto
     * @return
     */
    int createComment(CommentDto commentDto);

    /**
     * 根据商品ID分页查询评论
     * @param start
     * @param length
     * @param productId
     * @param orderByCol
     * @param orderBySort
     * @return
     */
    DataTablesResult findCommentSearchPage(int start, int length, Integer productId, String orderByCol, String orderBySort);

    /**
     * 根据ID查找评论
     * @param id
     * @return
     */
    TbComment findCommentById(Integer id);

    /**
     * 根据父评论ID查找评论
     * @param parent_id
     * @return
     */
    List<TbComment> findCommentByParentId(Integer parent_id);

    /**
     * 删除评论
     * @param id
     * @return
     */
    boolean del(Integer id);
}
