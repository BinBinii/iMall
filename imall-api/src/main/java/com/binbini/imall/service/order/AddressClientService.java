package com.binbini.imall.service.order;

import com.binbini.imall.pojo.TbAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "IMALL-PROVIDER-ORDER")
public interface AddressClientService {
    @PostMapping("/address/create")
    public int createAddress(@RequestBody TbAddress tbAddress);

    @PostMapping("/address/update")
    public int updateAddress(@RequestBody TbAddress tbAddress);

    @PostMapping("/address/delete")
    public int deleteAddressById(@RequestParam("id") Integer id);

    @GetMapping("/address/get/{uid}")
    public List<TbAddress> getAllAddressById(@PathVariable("uid") Integer uid);
}
