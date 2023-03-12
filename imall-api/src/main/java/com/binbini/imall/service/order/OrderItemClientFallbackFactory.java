package com.binbini.imall.service.order;

import com.binbini.imall.vo.OrderItemListVo;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/25/16:01
 * @Description:
 */
public class OrderItemClientFallbackFactory implements FallbackFactory {
    @Override
    public OrderItemClientService create(Throwable throwable) {
        return new OrderItemClientService() {
            @Override
            public List<OrderItemListVo> findOrderItemByUserId(Integer userId, Integer type) {
                return null;
            }
        };
    }
}
