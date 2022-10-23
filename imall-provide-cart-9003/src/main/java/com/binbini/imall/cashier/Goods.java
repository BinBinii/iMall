package com.binbini.imall.cashier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: BinBin
 * @Date: 2022/10/17/18:51
 * @Description:
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Goods {
    private Integer id;

    private String name;

    private Integer price;

    private String unit;
}
