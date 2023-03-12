package com.binbini.imall.controller;

import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.pojo.TbOrder;
import com.binbini.imall.service.OrderService;
import com.binbini.imall.vo.Render;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/18:57
 * @Description:
 */
@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public Object createOrder(@RequestBody OrderDto orderDto) {
        TbOrder result = orderService.createOrder(orderDto);
        if (result == null) {
            return Render.fail("Failed to generate order.");
        }
        return Render.ok(result);
    }

    @PostMapping("pay")
    public Object pay(@RequestParam("payNumber") String payNumber) {
        int result = orderService.pay(payNumber);
        if (result == 0) {
            return Render.fail("Parameter is empty.");
        }
        if (result == -2) {
            return Render.fail("An error occurred on the server. The administrator is solving the problem for you :D");
        }
        if (result == -1) {
            return Render.fail("The order could not be found.");
        }
        return Render.ok(result);
    }

    @GetMapping("get/{id}")
    public TbOrder findById(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

    @PostMapping("receipt/{id}")
    public Integer receipt(@PathVariable("id") Integer orderId) {
        return orderService.receipt(orderId);
    }

    @PostMapping("comment/{id}")
    public Integer comment(@PathVariable("id") Integer orderId) {
       return orderService.comment(orderId);
    }

    public TbOrder hystrixGetTbOrder(@PathVariable("id") Integer id) {
        return new TbOrder()
                .setId(id)
                .setOrder_number("id => " + id + " No corresponding information, null -- @Hystrix");
    }

}
