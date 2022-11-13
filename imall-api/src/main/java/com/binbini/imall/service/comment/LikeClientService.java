package com.binbini.imall.service.comment;

import com.binbini.imall.vo.LikeCountVo;
import com.binbini.imall.vo.LikeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/16:16
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-COMMENT", fallbackFactory = LikeClientFallbackFactory.class)
public interface LikeClientService {

    @PostMapping("/like/status")
    public int likeStatus(@RequestParam("commentId") Integer commentId,
                          @RequestParam("likeUserId") Integer likeUserId);

    @PostMapping("/like/get/data")
    public List<LikeVo> getLikedData(@RequestParam("commentId") Integer commentId);

    @PostMapping("/like/get/count")
    public List<LikeCountVo> getLikedCountData(@RequestParam("commentId") Integer commentId);

}
