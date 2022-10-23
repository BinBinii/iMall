package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.mapper.TbBannerSearchItemMapper;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.service.BannerSearchItemService;
import com.binbini.imall.vo.BannerSearchItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/09:42
 * @Description:
 */
@Service
public class BannerSearchItemServiceImpl implements BannerSearchItemService {

    @Autowired
    private TbBannerSearchItemMapper tbBannerSearchItemMapper;
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public int createBannerSearchItem(TbBannerSearchItem tbBannerSearchItem) {
        QueryWrapper<TbBannerSearchItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("search_id", tbBannerSearchItem.getSearch_id());
        queryWrapper.orderByAsc("sort");
        List<TbBannerSearchItem> inDBList = tbBannerSearchItemMapper.selectList(queryWrapper);
        TbBannerSearchItem last = inDBList.get(inDBList.size() - 1);
        tbBannerSearchItem.setSort(last.getSort() + 1);
        if (tbBannerSearchItem.getSearch_id().equals("") || tbBannerSearchItem.getProduct_id().equals("")) {
            return 0;
        }
        tbBannerSearchItem.setCreated(new Date())
                .setUpdated(new Date());
        if (tbBannerSearchItemMapper.insert(tbBannerSearchItem) != 1) {
            return -1;
        }
        return 1;
    }

    @Override
    public List<BannerSearchItemVo> findAllBannerSearchItemBySearchId(Integer search_id) {
        List<BannerSearchItemVo> result = new ArrayList<>();
        QueryWrapper<TbBannerSearchItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("search_id", search_id);
        List<TbBannerSearchItem> tbBannerSearchItems = tbBannerSearchItemMapper.selectList(queryWrapper);
        for (TbBannerSearchItem item:tbBannerSearchItems) {
            TbItem tbItem = tbItemMapper.selectById(item.getProduct_id());
            BannerSearchItemVo bannerSearchItemVo = new BannerSearchItemVo();
            bannerSearchItemVo.setId(item.getId())
                    .setItem_id(tbItem.getId())
                    .setTitle(tbItem.getTitle())
                    .setImage(tbItem.getImage());
            result.add(bannerSearchItemVo);
        }
        return result;
    }

    @Override
    public TbBannerSearchItem findBannerSearchItemById(Integer id) {
        return tbBannerSearchItemMapper.selectById(id);
    }

    @Override
    public boolean update(TbBannerSearchItem tbBannerSearchItem) {
        if (tbBannerSearchItem.getSearch_id().equals("") || tbBannerSearchItem.getProduct_id().equals("")) {
            return false;
        }
        TbBannerSearchItem inDb = tbBannerSearchItemMapper.selectById(tbBannerSearchItem.getId());
        inDb.setSearch_id(tbBannerSearchItem.getSearch_id())
                .setProduct_id(tbBannerSearchItem.getProduct_id())
                .setUpdated(new Date());
        if (tbBannerSearchItemMapper.updateById(inDb) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSort(List<Integer> ids) {
        List<TbBannerSearchItem> list = new ArrayList<>();
        for (Integer id:ids) {
            TbBannerSearchItem tbBannerSearchItem = tbBannerSearchItemMapper.selectById(id);
            list.add(tbBannerSearchItem);
        }
        for (int i = 0; i < list.size(); i++) {
            TbBannerSearchItem tbBannerSearchItem = list.get(i);
            tbBannerSearchItem.setSort(i + 1);
            if (tbBannerSearchItemMapper.updateById(tbBannerSearchItem) != 1) {
                // TODO 回滚
            }
        }
        return true;
    }

    @Override
    public boolean del(Integer id) {
        return tbBannerSearchItemMapper.deleteById(id) == 1;
    }
}
