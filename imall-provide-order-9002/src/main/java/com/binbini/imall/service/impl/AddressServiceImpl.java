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
    private TbAddressMapper tbAddressMapper;
    @Override
    public int createAddress(TbAddress tbAddress) {
        List<TbAddress> list = tbAddressMapper.selectList(new QueryWrapper<TbAddress>().eq("user_id", tbAddress.getUser_id()));
        if (list.size() == 0) {
            tbAddress.setIs_default(1);
        } else {
            tbAddress.setIs_default(0);
        }
        return tbAddressMapper.insert(tbAddress);
    }

    @Override
    public int updateAddress(TbAddress tbAddress) {
        if (tbAddress.getIs_default().equals(1)) {
            QueryWrapper<TbAddress> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", tbAddress.getUser_id()).eq("is_default", 1);
            TbAddress defaultTbAddress = tbAddressMapper.selectOne(queryWrapper);
            if (defaultTbAddress != null) {
                defaultTbAddress.setIs_default(0);
                tbAddressMapper.updateById(defaultTbAddress);
            }

        }
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
