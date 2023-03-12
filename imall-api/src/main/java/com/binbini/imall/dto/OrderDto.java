package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/18:14
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDto implements Serializable {

    private String order_number;            // 订单号

    private double payment;                 // 实付金额

    private Integer payment_type;           // 支付类型     1：在线支付  2：货到付款

    private double post_fee;                // 邮费

    private Integer user_id;                // 用户ID

    private String buyer_message;           // 买家留言

    private String buyer_nick;              // 买家昵称

    private Integer address_id;             // 收货地址ID

    private List<OrderItemDto> orderItemDtoList;

}
