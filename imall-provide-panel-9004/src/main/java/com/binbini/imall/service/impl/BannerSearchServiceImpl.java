package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.mapper.TbBannerSearchItemMapper;
import com.binbini.imall.mapper.TbBannerSearchMapper;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbBannerSearch;
import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.service.BannerSearchService;
import com.binbini.imall.vo.BannerSearchItemVo;
import com.binbini.imall.vo.BannerSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/09:41
 * @Description:
 */
@Service
public class BannerSearchServiceImpl implements BannerSearchService {

    @Autowired
    private TbBannerSearchMapper tbBannerSearchMapper;
    @Autowired
    private TbBannerSearchItemMapper tbBannerSearchItemMapper;
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public int createBannerSearch(TbBannerSearch tbBannerSearch) {
        QueryWrapper<TbBannerSearch> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<TbBannerSearch> inDBList = tbBannerSearchMapper.selectList(queryWrapper);
        TbBannerSearch last = inDBList.get(inDBList.size() - 1);
        tbBannerSearch.setSort(last.getSort() + 1);
        if (tbBannerSearch.getTitle().equals("") || tbBannerSearch.getSort().equals("")) {
            return 0;
        }
        tbBannerSearch.setStatus(1)
                .setCreated(new Date())
                .setUpdated(new Date());
        if (tbBannerSearchMapper.insert(tbBannerSearch) != 1) {
            return -1;
        }
        return 1;
    }

    @Override
    public List<BannerSearchVo> findAllBannerSearch() {
        List<BannerSearchVo> result = new ArrayList<>();
        // 数据库数据集合
        QueryWrapper<TbBannerSearch> tbBannerSearchQueryWrapper = new QueryWrapper<>();
        tbBannerSearchQueryWrapper.orderByAsc("sort");
        List<TbBannerSearch> tbBannerSearches = tbBannerSearchMapper.selectList(tbBannerSearchQueryWrapper);
        QueryWrapper<TbBannerSearchItem> tbBannerSearchItemQueryWrapper  = new QueryWrapper<>();
        tbBannerSearchItemQueryWrapper.orderByAsc("sort");
        List<TbBannerSearchItem> tbBannerSearchItems = tbBannerSearchItemMapper.selectList(tbBannerSearchItemQueryWrapper);
        // 循环TbBannerSearch列表
        for (TbBannerSearch tbBannerSearch:tbBannerSearches) {
            // Banner Search实例
            BannerSearchVo bannerSearchVo = new BannerSearchVo();
            List<BannerSearchItemVo> bannerSearchItemVoList = new ArrayList<>();
            // 循环TbBannerSearchItem列表
            for (TbBannerSearchItem tbBannerSearchItem:tbBannerSearchItems) {
                // 查找TbBannerSearchItem表内对应的TbBannerSearch ID
                if (tbBannerSearchItem.getSearch_id().equals(tbBannerSearch.getId())) {
                    TbItem tbItem = tbItemMapper.selectById(tbBannerSearchItem.getProduct_id());
                    BannerSearchItemVo bannerSearchItemVo = new BannerSearchItemVo();
                    bannerSearchItemVo.setId(tbBannerSearchItem.getId())
                            .setItem_id(tbItem.getId())
                            .setTitle(tbItem.getTitle())
                            .setImage(tbItem.getImage());
                    bannerSearchItemVoList.add(bannerSearchItemVo);
                }
            }
            bannerSearchVo.setBanner_search_id(tbBannerSearch.getId())
                    .setTitle(tbBannerSearch.getTitle())
                    .setSort(tbBannerSearch.getSort())
                    .setItemList(bannerSearchItemVoList);
            result.add(bannerSearchVo);
        }
        return result;
    }

    @Override
    public TbBannerSearch findBannerSearchById(Integer id) {
        return tbBannerSearchMapper.selectById(id);
    }

    @Override
    public boolean update(TbBannerSearch tbBannerSearch) {
        if (tbBannerSearch.getTitle().equals("") || tbBannerSearch.getSort().equals("")) {
            return false;
        }
        tbBannerSearch.setUpdated(new Date());
        if (tbBannerSearchMapper.updateById(tbBannerSearch) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean del(Integer id) {
        return tbBannerSearchMapper.deleteById(id) == 1;
    }
}
