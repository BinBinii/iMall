package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbAddress;
import com.binbini.imall.service.AddressService;
import com.binbini.imall.vo.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("create")
    public int createAddress(@RequestBody TbAddress tbAddress){
        return addressService.createAddress(tbAddress);
    }

    @PostMapping("update")
    public int updateAddress(@RequestBody TbAddress tbAddress){
        return addressService.updateAddress(tbAddress);
    }

    @PostMapping("delete")
    public int deleteAddressById(@RequestParam("id") Integer id){
        return addressService.deleteAddreesById(id);
    }

    @GetMapping("get/{uid}")
    public List<TbAddress> getAllAddressById(@PathVariable("uid") Integer uid){
        return addressService.getAllAddressById(uid);
    }

}
