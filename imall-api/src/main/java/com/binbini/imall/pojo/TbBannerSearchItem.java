package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/09:34
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbBannerSearchItem {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                         // 自增ID

    private Integer search_id;                  // 所属Banner Search

    private Integer product_id;                 // 商品ID

    private Integer sort;                       // 排序

    private Date created;                       // 创建时间

    private Date updated;                       // 更新时间
}
