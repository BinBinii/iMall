package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/11/19/14:47
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItemDto implements Serializable {
    private Integer item_id;        // 商品id

    private Integer version;        // 商品版本

    private Integer color;          // 商品颜色

    private Integer num;            // 购买数量
}
