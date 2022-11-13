package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/10:54
 * @Description: 点赞内容表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbLikeContent {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                 // 自增ID

    private Integer comment_id;         // 评论ID

    private Integer like_number;        // 点赞数

    private Integer comment_number;     // 评论数

    private Integer create_user;        // 创建人ID

    private Date created;               // 创建时间

    private Date updated;               // 更新时间
}
