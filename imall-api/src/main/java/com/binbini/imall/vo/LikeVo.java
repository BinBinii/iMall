package com.binbini.imall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/10:50
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LikeVo implements Serializable {
    private Integer commentId;      // 点赞评论ID

    private Integer likeUserId;     // 点赞人ID

    private Integer status;         // 点赞状态 0 取消 1 点赞
}
