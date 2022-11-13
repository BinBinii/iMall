package com.binbini.imall.service.comment;

import com.binbini.imall.dto.CommentDto;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/09:43
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-COMMENT", fallbackFactory = CommentClientFallbackFactory.class)
public interface CommentClientService {

    @PostMapping("/comment/create")
    public int createComment(@RequestBody CommentDto commentDto);

    @GetMapping("/comment/get/page")
    public DataTablesResult findCommentSearchPage(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam("productId") Integer productId,
                                                  @RequestParam("orderByCol") String orderByCol,
                                                  @RequestParam("orderBySort") String orderBySort);

    @GetMapping("/comment/get")
    public TbComment findCommentById(@RequestParam("id") Integer id);

    @GetMapping("/comment/get/list")
    public List<TbComment> findCommentByParentId(@RequestParam("parent_id") Integer parent_id);

    @PostMapping("/comment/del")
    public boolean del(@RequestParam("id") Integer id);
}
