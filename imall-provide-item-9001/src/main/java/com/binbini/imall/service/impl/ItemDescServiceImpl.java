package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.dto.ItemDescDto;
import com.binbini.imall.mapper.TbItemDescMapper;
import com.binbini.imall.pojo.TbItemDesc;
import com.binbini.imall.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/09:28
 * @Description:
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public int createItemDesc(ItemDescDto itemDescDto) {
        if (itemDescDto.getItem_id().equals("") || itemDescDto.getItem_desc().equals("")) {
            return 0;
        }
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItem_id(itemDescDto.getItem_id())
                .setItem_desc(itemDescDto.getItem_desc())
                .setCreated(new Date())
                .setUpdated(new Date());
        if (tbItemDescMapper.insert(tbItemDesc) != 1) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean update(ItemDescDto itemDescDto) {
        if (itemDescDto.getItem_id().equals("") || itemDescDto.getItem_desc().equals("")) {
            return false;
        }
        QueryWrapper<TbItemDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemDescDto.getItem_id());
        TbItemDesc tbItemDesc = tbItemDescMapper.selectOne(queryWrapper);
        if (tbItemDesc == null) {
            return false;
        }
        if (tbItemDescMapper.updateById(tbItemDesc) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean del(Integer item_id) {
        QueryWrapper<TbItemDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", item_id);
        return tbItemDescMapper.delete(queryWrapper) == 1;
    }
}
