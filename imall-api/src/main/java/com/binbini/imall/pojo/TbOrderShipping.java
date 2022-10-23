package com.binbini.imall.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/18:40
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbOrderShipping implements Serializable {
    private Integer order_id;                   // 订单ID

    private String receiver_name;               // 收货人名字

    private String receiver_phone;              // 固定电话

    private String receiver_mobile;             // 移动电话

    private String receiver_state;              // 省份

    private String receiver_city;               // 城市

    private String receiver_district;           // 区

    private String receiver_address;            // 收货地址

    private String receiver_zip;                // 邮政编码

    private Date created;                       // 创建时间

    private Date updated;                       // 更新时间


}