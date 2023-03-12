package com.binbini.imall.service.order;

import com.binbini.imall.vo.OrderItemListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/25/16:01
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-ORDER", fallbackFactory = OrderItemClientFallbackFactory.class)
public interface OrderItemClientService {

    @GetMapping("/order/item/list")
    public List<OrderItemListVo> findOrderItemByUserId(@RequestParam("userId") Integer userId, @RequestParam("type") Integer type);

}
