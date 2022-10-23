package com.binbini.imall.controller;

import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.dto.pay.OrderAppointmentForm;
import com.binbini.imall.dto.pay.PayOrderDto;
import com.binbini.imall.pojo.TbOrder;
import com.binbini.imall.service.order.OrderClientService;
import com.binbini.imall.service.pay.PayClientService;
import com.binbini.imall.utils.IdGen;
import com.binbini.imall.vo.Render;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/19:28
 * @Description:
 */
@RestController
public class OrderConsumerController {

    @Autowired
    private OrderClientService orderClientService;
    @Autowired
    private PayClientService payClientService;

    @PostMapping("/s/create")
    public Object createOrder(@RequestBody OrderDto orderDto) {
        String payNumber = IdGen.uuid();
        orderDto.setOrder_number(payNumber);
        PayOrderDto payOrderDto = new PayOrderDto();
        List<OrderAppointmentForm> orderAppointmentFormList = new ArrayList<>();
        OrderAppointmentForm orderAppointmentForm = new OrderAppointmentForm();
        orderAppointmentForm.setMoney(orderDto.getPayment())
                        .setCommodity_name("iMall产品")
                        .setMerchant_name("iMall");
        orderAppointmentFormList.add(orderAppointmentForm);
        payOrderDto.setUser_info_id(orderDto.getUser_id())
                .setPayNumber(payNumber)
                .setOrderAppointmentForms(orderAppointmentFormList);
        payClientService.order(payOrderDto);
        return orderClientService.createOrder(orderDto);
    }

    @PostMapping("/s/pay/{payNumber}")
    public Object pay(@PathVariable("payNumber") String payNumber) {
        Object result = payClientService.pay(payNumber);
        ObjectMapper objectMapper = new ObjectMapper();
        Render render = objectMapper.convertValue(result, Render.class);
        if (render.getError()) {
            return render.getMsg();
        }
        return orderClientService.pay(payNumber);
    }

    @GetMapping("/s/get/{id}")
    public TbOrder findById(@PathVariable("id") Integer id) {
        return orderClientService.findById(id);
    }

}
