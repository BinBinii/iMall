package com.binbini.imall.cashier;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: BinBin
 * @Date: 2022/10/17/18:19
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GoodsItem {

    private Integer id;

    private String name;

    private double price;

    private Integer number;

    private String unit;

    private double money;

}
