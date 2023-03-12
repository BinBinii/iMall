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
 * @Date: 2022/09/15/16:41
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbOrder implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                     // 自增ID

    private String order_number;            // 订单号（由iPay生成）

    private double payment;                 // 实付金额

    private Integer payment_type;           // 支付类型     1：在线支付  2：货到付款

    private double post_fee;                // 邮费

    private Integer status;                 // 状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败

    private Date create_time;               // 订单创建时间

    private Date update_time;               // 订单更新时间

    private Date payment_time;              // 付款时间

    private Date consign_time;              // 发货时间

    private Date end_time;                  // 交易完成时间

    private Date close_time;                // 交易关闭时间

    private String shipping_name;           // 物流名称

    private String shipping_code;           // 物流单号

    private Integer user_id;                // 用户ID

    private String buyer_message;           // 买家留言

    private String buyer_nick;              // 买家昵称

    private Integer buyer_comment;          // 买家是否已经评价

    private String address_info;            // 收货信息
}
