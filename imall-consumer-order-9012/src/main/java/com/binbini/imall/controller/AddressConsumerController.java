package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbAddress;
import com.binbini.imall.service.order.AddressClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressConsumerController {
    @Autowired
    private AddressClientService addressClientService;
    @PostMapping("/s/address/create")
    public int createAddress(@RequestBody TbAddress tbAddress){
        return addressClientService.createAddress(tbAddress);
    }
    @PostMapping("/s/address/update")
    public int updateAddress(@RequestBody TbAddress tbAddress){
        return addressClientService.updateAddress(tbAddress);
    };

    @PostMapping("/s/address/delete")
    public int deleteAddressById(@RequestParam("id") Integer id){
        return addressClientService.deleteAddressById(id);
    };

    @GetMapping("/s/address/get/{uid}")
    public List<TbAddress> getAllAddressById(@PathVariable("uid") Integer uid){
        return addressClientService.getAllAddressById(uid);
    };



}
