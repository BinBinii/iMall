package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/18:30
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbOrderItem implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             // 自增ID

    private Integer item_id;        // 商品ID

    private Integer order_id;       // 订单ID

    private Integer num;            // 商品购买数量

    private String title;           // 商品标题

    private double price;           // 商品单价

    private double total_fee;       // 商品总金额

    private String pic_path;        // 商品图片地址
}
