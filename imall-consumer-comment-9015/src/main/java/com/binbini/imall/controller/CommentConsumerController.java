package com.binbini.imall.controller;

import com.binbini.imall.dto.CommentDto;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.service.comment.CommentClientService;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/09:42
 * @Description:
 */
@RestController
public class CommentConsumerController {
    @Autowired
    private CommentClientService commentClientService;

    @PostMapping("/s/create")
    public int createComment(@RequestBody CommentDto commentDto) {
        return commentClientService.createComment(commentDto);
    }

    @PostMapping("s/create/message")
    public int createCommentMessage(@RequestParam("parentId")Integer parentId,
                                    @RequestParam("userId")Integer userId,
                                    @RequestParam("content")String content) {
        return commentClientService.createCommentMessage(parentId, userId, content);
    }

    @GetMapping("/s/get/page")
    public DataTablesResult findCommentSearchPage(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam("productId") Integer productId,
                                                  @RequestParam("orderByCol") String orderByCol,
                                                  @RequestParam("orderBySort") String orderBySort) {
        return commentClientService.findCommentSearchPage(start, length, productId, orderByCol, orderBySort);
    }

    @GetMapping("/s/get")
    public TbComment findCommentById(@RequestParam("id") Integer id) {
        return commentClientService.findCommentById(id);
    }

    @GetMapping("/s/get/list")
    public DataTablesResult findCommentByParentId(@RequestParam("start") Integer start,
                                                  @RequestParam("length") Integer length,
                                                  @RequestParam("parent_id") Integer parent_id) {
        return commentClientService.findCommentByParentId(start, length, parent_id);
    }

    @PostMapping("/s/del")
    public boolean del(@RequestParam("id") Integer id) {
        return commentClientService.del(id);
    }
}
