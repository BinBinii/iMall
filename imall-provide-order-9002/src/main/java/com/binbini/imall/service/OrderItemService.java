package com.binbini.imall.service;

import com.binbini.imall.pojo.TbOrderItem;
import com.binbini.imall.vo.OrderItemListVo;
import com.binbini.imall.vo.OrderItemVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/19/15:19
 * @Description:
 */
@Service
public interface OrderItemService {

    /**
     * 根据用户ID查找订单详情
     * @param userId      用户ID
     * @param type        0:全部有效订单 1:未付款 2:待收货 3:订单回收站
     * @return
     */
    List<OrderItemListVo> findOrderItemByUserId(Integer userId, Integer type);

    /**
     * 根据订单ID查找订单详情
     * @param orderId      订单ID
     * @return
     */
    List<OrderItemVo> findOrderItemByOrderId(Integer orderId);

}
