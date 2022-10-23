package com.binbini.imall.service;

import com.binbini.imall.dto.ItemDescDto;
import org.springframework.stereotype.Service;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/09:27
 * @Description:
 */
@Service
public interface ItemDescService {

    int createItemDesc(ItemDescDto itemDescDto);

    boolean update(ItemDescDto itemDescDto);

    boolean del(Integer item_id);

}
