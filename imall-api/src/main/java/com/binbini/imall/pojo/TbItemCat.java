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
 * @Date: 2022/09/15/18:23
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbItemCat implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                 // 类目ID

    private Integer parent_id;          // 父分类ID=0时代表一级根分类

    private Integer product_id;         // 商品ID

    private String name;                // 分类名称

    private Integer status;             // 状态 1：启用 0：禁用

    private Integer sort_order;         // 排列序号

    private Integer is_parent;          // 是否为父分类 1为true 0为false

    private String icon;                // 图标

    private String remark;              // 备注

    private Date created;               // 创建时间

    private Date updated;               // 更新时间
}
