package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/10:58
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbPanelContent implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                 // 自增ID

    private Integer panel_id;           // 板块ID

    private Integer product_id;         // 商品ID

    private Integer sort_order;         // 板块内排序

    private String pic_url;             // 图片

    private String pic_url2;            // 轮播图片

    private String pic_url3;            // 轮播图片

    private Date created;               // 创建时间

    private Date updated;               // 更新时间
}
