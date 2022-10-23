package com.binbini.imall.dto.pay;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/07/10:04
 * @Description: 支付实体
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PayOrderDto implements Serializable {

    private Integer user_info_id;

    private String payNumber;

    List<OrderAppointmentForm> orderAppointmentForms;

}
