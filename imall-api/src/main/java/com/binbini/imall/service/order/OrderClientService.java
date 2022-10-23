package com.binbini.imall.service.order;

import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.pojo.TbOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/order/pay/{payNumber}")
    public Object pay(@PathVariable("payNumber") String payNumber);

    @GetMapping("/order/get/{id}")
    public TbOrder findById(@PathVariable("id") Integer id);

}
