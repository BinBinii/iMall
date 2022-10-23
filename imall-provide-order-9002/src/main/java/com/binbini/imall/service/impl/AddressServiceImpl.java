package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.binbini.imall.pojo.TbAddress;
import com.binbini.imall.mapper.TbAddressMapper;
import com.binbini.imall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    TbAddressMapper tbAddressMapper;
    @Override
    public int createAddress(TbAddress tbAddress) {
        return   tbAddressMapper.insert(tbAddress);
    }

    @Override
    public int updateAddress(TbAddress tbAddress) {
        return tbAddressMapper.updateById(tbAddress);
    }

    @Override
    public int deleteAddreesById(Integer id) {
        return tbAddressMapper.deleteById(id);
    }

    @Override
    public List<TbAddress> getAllAddressById(Integer uid) {
        QueryWrapper<TbAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        return tbAddressMapper.selectList(wrapper);
    }



}
