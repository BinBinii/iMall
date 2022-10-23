package com.binbini.imall.dto.pay;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/07/10:59
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderAppointmentForm implements Serializable {

    private double money;

    private String commodity_name;      // 商品名称

    private String merchant_name;       // 商户名称

}
