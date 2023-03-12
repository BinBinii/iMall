package com.binbini.imall.service;

import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.pojo.TbOrder;
import org.springframework.stereotype.Service;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/18:13
 * @Description:
 */
@Service
public interface OrderService {

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    TbOrder createOrder(OrderDto orderDto);

    /**
     * 支付订单
     * @param payNumber
     * @return
     */
    Integer pay(String payNumber);

    /**
     * 根据ID查询订单
     * @param id
     * @return
     */
    TbOrder findById(Integer id);

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    Integer receipt(Integer orderId);

    /**
     * 评价
     * @param orderId
     * @return
     */
    Integer comment(Integer orderId);

}
