package com.binbini.imall.controller;

import com.binbini.imall.service.comment.LikeClientService;
import com.binbini.imall.vo.LikeCountVo;
import com.binbini.imall.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/16:18
 * @Description:
 */
@RestController
public class LikeConsumerController {

    @Autowired
    private LikeClientService likeClientService;

    @PostMapping("/s/like/status")
    public int likeStatus(@RequestParam("commentId") Integer commentId,
                          @RequestParam("likeUserId") Integer likeUserId) {
        return likeClientService.likeStatus(commentId, likeUserId);
    }

    @PostMapping("/s/get/like/data")
    public List<LikeVo> getLikedData(@RequestParam("commentId") Integer commentId) {
        return likeClientService.getLikedData(commentId);
    }

    @PostMapping("/s/get/like/count")
    public List<LikeCountVo> getLikedCountData(@RequestParam("commentId") Integer commentId) {
        return likeClientService.getLikedCountData(commentId);
    }
}
