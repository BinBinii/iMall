package com.binbini.imall.service;

import com.binbini.imall.vo.LikeCountVo;
import com.binbini.imall.vo.LikeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/11:00
 * @Description:
 */
@Service
public interface LikeService {

    /**
     * 更改点赞状态
     * @param commentId
     * @param likeUserId
     * @return
     */
    int likeStatus(Integer commentId, Integer likeUserId);

    /**
     * 统计点赞变化的情况
     * @return
     */
    List<LikeVo> getLikedData(Integer commentId);

    /**
     * 统计点赞数量
     * @return
     */
    List<LikeCountVo> getLikedCountData(Integer commentId);
}
