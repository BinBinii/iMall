package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/10:50
 * @Description: 点赞表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             // 自增ID

    private Integer comment_id;     // 点赞评论ID

    private Integer like_user_id;   // 点赞人ID

    private Integer status;         // 0 取消 1 点赞

    private Date created;           // 创建时间

    private Date updated;           // 更新时间
}
