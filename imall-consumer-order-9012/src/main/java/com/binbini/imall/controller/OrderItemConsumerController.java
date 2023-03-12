package com.binbini.imall.controller;

import com.binbini.imall.service.order.OrderItemClientService;
import com.binbini.imall.vo.OrderItemListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/25/16:01
 * @Description:
 */
@RestController
public class OrderItemConsumerController {

    @Autowired
    private OrderItemClientService orderItemClientService;

    @GetMapping("/s/item/list")
    public List<OrderItemListVo> findOrderItemByUserId(@RequestParam("userId") Integer userId, @RequestParam("type") Integer type) {
        return orderItemClientService.findOrderItemByUserId(userId, type);
    }
}
