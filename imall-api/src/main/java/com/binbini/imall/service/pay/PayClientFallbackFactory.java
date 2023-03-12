package com.binbini.imall.service.pay;

import com.binbini.imall.dto.pay.PayOrderDto;
import feign.hystrix.FallbackFactory;

/**
 * @Author: BinBin
 * @Date: 2022/09/07/14:52
 * @Description:
 */
public class PayClientFallbackFactory implements FallbackFactory {


    @Override
    public PayClientService create(Throwable throwable) {
        return new PayClientService() {
            @Override
            public Object order(PayOrderDto orderDto) {
                return "Service Degradation.";
            }

            @Override
            public Object pay(String payNumber, String payPassword) {
                return false;
            }
        };
    }
}
