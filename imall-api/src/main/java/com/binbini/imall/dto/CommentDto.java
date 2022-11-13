package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/08:36
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CommentDto implements Serializable {
    private Integer user_id;                    // 用户ID

    private Integer product_id;                 // 商品ID

    private Integer product_version;            // 商品版本

    private Integer product_color;              // 商品颜色

    private double score;                       // 评分

    private String content;                     // 评价内容

    private Integer is_parent;                  // 是否为父评论

    private Integer parent_id;                  // 父评论ID
}
