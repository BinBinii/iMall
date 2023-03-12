package com.binbini.imall.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/11/19/16:24
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IPayOrderVo implements Serializable {
    private Integer user_id;

    private String number;

    private String merchant_name;

    private String commodity_name;

    private double money;
}
