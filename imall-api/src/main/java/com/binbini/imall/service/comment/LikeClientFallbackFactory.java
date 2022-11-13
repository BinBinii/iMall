package com.binbini.imall.service.comment;

import com.binbini.imall.vo.LikeCountVo;
import com.binbini.imall.vo.LikeVo;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/16:16
 * @Description:
 */
public class LikeClientFallbackFactory implements FallbackFactory {
    @Override
    public LikeClientService create(Throwable throwable) {
        return new LikeClientService() {
            @Override
            public int likeStatus(Integer commentId, Integer likeUserId) {
                return 0;
            }

            @Override
            public List<LikeVo> getLikedData(Integer commentId) {
                return null;
            }

            @Override
            public List<LikeCountVo> getLikedCountData(Integer commentId) {
                return null;
            }
        };
    }
}
