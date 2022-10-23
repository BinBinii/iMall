package com.binbini.imall.service.pay;

import com.binbini.imall.dto.pay.PayOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: BinBin
 * @Date: 2022/09/07/14:51
 * @Description:
 */
@Component
@FeignClient(value = "IPAY-PROVIDER-PAY", fallbackFactory = PayClientFallbackFactory.class)
public interface PayClientService {

    @PostMapping("/pay/order")
    public Object order(PayOrderDto orderDto);

    @PostMapping("/pay/anon/{payNumber}")
    public Object pay(@PathVariable("payNumber") String payNumber);

}
