package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/19:05
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ItemDto implements Serializable {
    private Integer id;

    private String title;              // 商品标题

    private String sell_point;         // 商品卖点

    private String version;            // 版本

    private String color;              // 颜色

    private String price;              // 商品价格

    private Integer num;               // 库存数量

    private Integer limit_num;         // 售卖数量限制

    private String image;              // 商品图片

    private Integer cid;               // 所属分类

    private Integer status;            // 商品状态 1正常 0下架
}
