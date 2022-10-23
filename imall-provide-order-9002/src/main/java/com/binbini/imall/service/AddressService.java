package com.binbini.imall.service;

import com.binbini.imall.pojo.TbAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    /**
     * 创建地址
     * @param tbAddress
     * @return
     */
    int createAddress(TbAddress tbAddress);

    /**
     * 修改地址
     */
    int updateAddress(TbAddress tbAddress);

    /**
     * 删除地址
     */
    int deleteAddreesById(Integer id);


    List<TbAddress> getAllAddressById(Integer uid);
}
