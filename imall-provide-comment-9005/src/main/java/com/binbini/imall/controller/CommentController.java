package com.binbini.imall.controller;

import com.binbini.imall.dto.CommentDto;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.service.CommentService;
import com.binbini.imall.vo.DataTablesResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/09:28
 * @Description:
 */
@RestController
@RequestMapping(value = "/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("create")
    public int createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @GetMapping("get/page")
    public DataTablesResult findCommentSearchPage(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam("productId") Integer productId,
                                                  @RequestParam("orderByCol") String orderByCol,
                                                  @RequestParam("orderBySort") String orderBySort) {
        return commentService.findCommentSearchPage(start, length, productId, orderByCol, orderBySort);
    }

    @GetMapping("get")
    @HystrixCommand(fallbackMethod = "hystrixGetTbComment")
    public TbComment findCommentById(@RequestParam("id") Integer id) {
        return commentService.findCommentById(id);
    }

    @GetMapping("get/list")
    public List<TbComment> findCommentByParentId(@RequestParam("parent_id") Integer parent_id) {
        return commentService.findCommentByParentId(parent_id);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return commentService.del(id);
    }

    public TbComment hystrixGetTbComment(@RequestParam("id") Integer id) {
        return new TbComment()
                .setId(id)
                .setContent("id => " + id + " No corresponding information, null -- @Hystrix");
    }

}
