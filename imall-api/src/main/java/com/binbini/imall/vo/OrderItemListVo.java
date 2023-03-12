package com.binbini.imall.vo;

import com.binbini.imall.pojo.TbOrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/24/14:41
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItemListVo implements Serializable {

    private Integer orderId;

    private Integer status;

    private List<TbOrderItem> data;
}
