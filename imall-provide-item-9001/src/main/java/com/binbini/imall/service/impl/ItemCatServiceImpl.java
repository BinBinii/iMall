package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.dto.ItemCatDto;
import com.binbini.imall.mapper.TbItemCatMapper;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.service.ItemCatService;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.ItemCatChildrenVo;
import com.binbini.imall.vo.ItemCatVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/08:50
 * @Description:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public int createItemCat(ItemCatDto itemCatDto) {
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("parent_id", itemCatDto.getParent_id());
        queryWrapper.orderByAsc("sort_order");
        List<TbItemCat> inDBList = tbItemCatMapper.selectList(queryWrapper);
        if (inDBList == null || inDBList.size() == 0) {
            itemCatDto.setSort_order(1);
        } else {
            TbItemCat last = inDBList.get(inDBList.size() - 1);
            itemCatDto.setSort_order(last.getSort_order() + 1);
        }
        if (itemCatDto.getParent_id().equals("") || itemCatDto.getName().equals("") || itemCatDto.getSort_order().equals("") || itemCatDto.getIs_parent().equals("")) {
            return 0;
        }
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setParent_id(itemCatDto.getParent_id())
                .setName(itemCatDto.getName())
                .setProduct_id(itemCatDto.getProduct_id())
                .setStatus(1)
                .setSort_order(itemCatDto.getSort_order())
                .setIs_parent(itemCatDto.getIs_parent())
                .setIcon(itemCatDto.getIcon())
                .setRemark(itemCatDto.getRemark())
                .setCreated(new Date())
                .setUpdated(new Date());
        if (tbItemCatMapper.insert(tbItemCat) != 1) {
            return -1;
        }
        return 1;
    }

    @Override
    public DataTablesResult findItemCatSearchPage(int start, int length, String search, int parent_id, String minDate, String maxDate) {
        DataTablesResult result = new DataTablesResult();
        PageHelper.startPage(start, length);
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
        if (parent_id != -1) {
            queryWrapper.like("parent_id", parent_id);
        }
        if (!search.equals("")) {
            queryWrapper.like("name", search).or().like("id", search);
        }
        if (maxDate != null && !maxDate.equals("") && minDate != null && !minDate.equals("")) {
            queryWrapper.between("created", minDate, maxDate);
        }
//        queryWrapper.orderBy(true, true, "id").orderBy(true, false, "sort_order");
        queryWrapper.orderByAsc("parent_id").orderByAsc("sort_order");
        List<TbItemCat> list = tbItemCatMapper.selectList(queryWrapper);
        PageInfo<TbItemCat> pageInfo = new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getAllItemCatCount().getRecordsTotal());

        result.setSuccess(true);
        result.setData(list);
        return result;
    }

    @Override
    public List<TbItemCat> findItemCatByAllParent() {
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("parent_id", 0);
        queryWrapper.orderByAsc("sort_order");
        List<TbItemCat> result = tbItemCatMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<ItemCatVo> cascaderItemCat() {
        List<ItemCatVo> result = new ArrayList<>();
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("parent_id", 0).notLike("id", -1);
        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectList(queryWrapper);
        for (TbItemCat tbItemCat:tbItemCatList) {
            ItemCatVo itemCatVo = new ItemCatVo();
            List<ItemCatChildrenVo> itemCatChildrenVos = new ArrayList<>();
            itemCatVo.setValue(tbItemCat.getId())
                    .setLabel(tbItemCat.getName());
            QueryWrapper<TbItemCat> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.like("parent_id", tbItemCat.getId()).orderByAsc("sort_order");
            List<TbItemCat> tbItemCatList2 = tbItemCatMapper.selectList(queryWrapper2);
            for (TbItemCat tbItemCat2:tbItemCatList2) {
                ItemCatChildrenVo itemCatChildrenVo = new ItemCatChildrenVo();
                itemCatChildrenVo.setValue(tbItemCat2.getId())
                        .setLabel(tbItemCat2.getName())
                        .setIcon(tbItemCat2.getIcon());
                itemCatChildrenVos.add(itemCatChildrenVo);
            }
            itemCatVo.setChildren(itemCatChildrenVos);
            result.add(itemCatVo);
        }
        return result;
    }

    @Override
    public TbItemCat findItemCatById(Integer id) {
        TbItemCat tbItemCat = tbItemCatMapper.selectById(id);
        String originName = tbItemCat.getName();
        if (tbItemCat.getParent_id() != 0) {
            TbItemCat tbItemCatParent = tbItemCatMapper.selectById(tbItemCat.getParent_id());
            tbItemCat.setName(tbItemCatParent.getName() + " / " + originName);
        }
        return tbItemCat;
    }

    @Override
    public List<TbItemCat> findItemCatsByParent_id(Integer parent_id) {
        if (parent_id == null) {
            return null;
        }
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parent_id);
        queryWrapper.orderByAsc("sort_order");
        return tbItemCatMapper.selectList(queryWrapper);
    }

    @Override
    public boolean update(ItemCatDto itemCatDto) {
        if (itemCatDto.getId().equals("") || itemCatDto.getParent_id().equals("") || itemCatDto.getName().equals("") || itemCatDto.getSort_order().equals("") || itemCatDto.getIs_parent().equals("")) {
            return false;
        }
        TbItemCat tbItemCat = tbItemCatMapper.selectById(itemCatDto.getId());
        tbItemCat.setParent_id(itemCatDto.getParent_id())
                .setName(itemCatDto.getName())
                .setStatus(itemCatDto.getStatus())
                .setSort_order(itemCatDto.getSort_order())
                .setIs_parent(itemCatDto.getIs_parent())
                .setIcon(itemCatDto.getIcon())
                .setRemark(itemCatDto.getRemark())
                .setProduct_id(itemCatDto.getProduct_id());
        if (tbItemCatMapper.updateById(tbItemCat) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSortOrder(List<TbItemCat> list) {
        for (int i = 0; i < list.size(); i++) {
            TbItemCat tbItemCat = list.get(i);
            tbItemCat.setSort_order(i + 1);
            if (tbItemCatMapper.updateById(tbItemCat) != 1) {
                // TODO 回滚
            }
        }
        return true;
    }

    @Override
    public boolean del(Integer id) {
        return tbItemCatMapper.deleteById(id) == 1;
    }

    private DataTablesResult getAllItemCatCount() {
        long count = tbItemCatMapper.selectCount(new QueryWrapper<>());
        DataTablesResult result = new DataTablesResult();
        result.setRecordsTotal((int) count);
        return result;
    }
}
