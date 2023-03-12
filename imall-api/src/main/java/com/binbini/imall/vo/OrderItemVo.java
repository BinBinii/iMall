package com.binbini.imall.vo;

import com.binbini.imall.pojo.TbOrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/24/14:26
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItemVo implements Serializable {
    private List<TbOrderItem> data;     // 订单商品详情

    private double payment;             // 订单实付金额

    private double post_fee;            // 邮费

    private String order_number;        // 订单号

    private Date create_time;           // 订单创建时间

    private Date payment_time;          // 付款时间

    private Date consign_time;          // 发货时间

    private Date end_time;              // 交易完成时间
}
