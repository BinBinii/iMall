package com.binbini.imall.controller;

import com.binbini.imall.service.LikeService;
import com.binbini.imall.vo.LikeCountVo;
import com.binbini.imall.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/14:49
 * @Description:
 */
@RestController
@RequestMapping(value = "/like/")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("status")
    public int likeStatus(@RequestParam("commentId") Integer commentId,
                          @RequestParam("likeUserId") Integer likeUserId) {
        return likeService.likeStatus(commentId, likeUserId);
    }

    @PostMapping("get/data")
    public List<LikeVo> getLikedData(@RequestParam("commentId") Integer commentId) {
        return likeService.getLikedData(commentId);
    }

    @PostMapping("get/count")
    public List<LikeCountVo> getLikedCountData(@RequestParam("commentId") Integer commentId) {
        return likeService.getLikedCountData(commentId);
    }

}
