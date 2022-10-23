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
public class TbPanel implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             // 板块ID

    private String name;            // 板块名称

    private Integer type;           // 板块类型 0轮播图 1手机 2穿戴 3笔记本｜平板 4摄影摄像 5家电 6生活电器 7智能家居

    private Integer sort_order;     // 板块排序

    private Integer limit_num;      // 限制商品数量

    private Integer status;         // 状态

    private String remark;          // 备注

    private Date created;           // 创建时间

    private Date updated;           // 更新时间

}
