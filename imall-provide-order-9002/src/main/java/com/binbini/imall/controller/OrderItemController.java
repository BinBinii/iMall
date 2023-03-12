package com.binbini.imall.controller;

import com.binbini.imall.service.OrderItemService;
import com.binbini.imall.vo.OrderItemListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/24/14:47
 * @Description:
 */
@RestController
@RequestMapping(value = "/order/item/")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("list")
    public List<OrderItemListVo> findOrderItemByUserId(@RequestParam("userId") Integer userId, @RequestParam("type") Integer type) {
        return orderItemService.findOrderItemByUserId(userId, type);
    }

}
