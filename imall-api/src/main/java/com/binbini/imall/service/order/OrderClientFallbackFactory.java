package com.binbini.imall.service.order;

import com.binbini.imall.dto.OrderDto;
import com.binbini.imall.pojo.TbOrder;
import feign.hystrix.FallbackFactory;

/**
 * @Author: BinBin
 * @Date: 2022/09/19/19:30
 * @Description:
 */
public class OrderClientFallbackFactory implements FallbackFactory {
    @Override
    public OrderClientService create(Throwable throwable) {
        return new OrderClientService() {
            @Override
            public Object createOrder(OrderDto orderDto) {
                return "Service Degradation.";
            }

            @Override
            public Object pay(String payNumber) {
                return "xD";
            }

            @Override
            public TbOrder findById(Integer id) {
                return new TbOrder()
                        .setId(id)
                        .setOrder_number("id => " + id + " There is no corresponding information. The client provided degraded information. The service has been shut down.");
            }

            @Override
            public Integer receipt(Integer orderId) {
                return null;
            }

            @Override
            public Integer comment(Integer orderId) {
                return null;
            }
        };
    }
}
