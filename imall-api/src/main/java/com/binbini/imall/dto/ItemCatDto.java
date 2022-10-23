package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/08:51
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ItemCatDto implements Serializable {
    private Integer id;                 // 类目ID

    private Integer parent_id;          // 父分类ID=0时代表一级根分类

    private String name;                // 分类名称

    private Integer status;             // 状态 1：启用 0：禁用

    private Integer sort_order;         // 排列序号

    private Integer is_parent;          // 是否为父分类 1为true 0为false

    private Integer product_id;         // 商品ID is_parent为0时

    private String icon;                // 图标

    private String remark;              // 备注
}
