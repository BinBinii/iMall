package com.binbini.imall.service.order;

import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.pojo.TbOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/19:29
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-ORDER", fallbackFactory = OrderClientFallbackFactory.class)
public interface OrderClientService {

    @PostMapping("/order/create")
    public Object createOrder(@RequestBody OrderDto orderDto);

    @PostMapping("/order/pay")
    public Object pay(@RequestParam("payNumber") String payNumber);

    @GetMapping("/order/get/{id}")
    public TbOrder findById(@PathVariable("id") Integer id);

    @PostMapping("/order/receipt/{id}")
    public Integer receipt(@PathVariable("id") Integer orderId);

    @PostMapping("/order/comment/{id}")
    public Integer comment(@PathVariable("id") Integer orderId);

}
