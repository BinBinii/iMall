package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.exception.IMallException;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.mapper.TbPanelContentMapper;
import com.binbini.imall.mapper.TbPanelMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.service.PanelContentService;
import com.binbini.imall.vo.DataTablesResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/11:11
 * @Description:
 */
@Service
public class PanelContentServiceImpl implements PanelContentService {

    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;
    @Autowired
    private TbPanelMapper tbPanelMapper;
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public int createPanelContent(TbPanelContent tbPanelContent) {
        QueryWrapper<TbPanelContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("panel_id", tbPanelContent.getPanel_id());
        queryWrapper.orderByAsc("sort_order");
        List<TbPanelContent> inDBList = tbPanelContentMapper.selectList(queryWrapper);
        if (inDBList == null || inDBList.size() == 0) {
            tbPanelContent.setSort_order(1);
        } else {
            TbPanelContent last = inDBList.get(inDBList.size() - 1);
            tbPanelContent.setSort_order(last.getSort_order() + 1);
        }
        TbPanel tbPanel = tbPanelMapper.selectById(tbPanelContent.getPanel_id());
        if ((tbPanelContent.getPanel_id().equals("") || tbPanelContent.getProduct_id().equals("") || tbPanelContent.getPic_url().equals(""))
            && tbPanel.getType() != 0) {
            TbItem tbItem = tbItemMapper.selectById(tbPanelContent.getProduct_id());
            String[] images = tbItem.getImage().split(",");
            for (int i = 0; i < images.length; i++) {
                if (i == 0) {
                    tbPanelContent.setPic_url(images[0]);
                }
                if (i == 1) {
                    tbPanelContent.setPic_url2(images[1]);
                }
                if (i == 2) {
                    tbPanelContent.setPic_url3(images[2]);
                }
            }
        }
        if (tbPanelContent.getPanel_id().equals("") || tbPanelContent.getProduct_id().equals("") || tbPanelContent.getPic_url().equals("")) {
            return 0;
        }
        tbPanelContent.setCreated(new Date())
                .setUpdated(new Date());
        if (tbPanelContentMapper.insert(tbPanelContent) != 1) {
            return -1;
        }
        return 1;
    }

    @Override
    public DataTablesResult findPanelContentSearchPage(int start, int length, String search, String minDate, String maxDate) {
        DataTablesResult result = new DataTablesResult();
        PageHelper.startPage(start, length);
        QueryWrapper<TbPanelContent> queryWrapper = new QueryWrapper<>();
        if (!search.equals("")) {
            queryWrapper.like("panel_id", search);
        }
        if (maxDate != null && !maxDate.equals("") && minDate != null && !minDate.equals("")) {
            queryWrapper.between("created", minDate, maxDate);
        }
        queryWrapper.orderByAsc("id");
        List<TbPanelContent> list = tbPanelContentMapper.selectList(queryWrapper);
        PageInfo<TbPanelContent> pageInfo = new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getAllPanelContentCount().getRecordsTotal());

        result.setSuccess(true);
        result.setData(list);
        return result;
    }

    @Override
    public TbPanelContent findPanelContentById(Integer id) {
       TbPanelContent tbPanelContent = tbPanelContentMapper.selectById(id);
       if (tbPanelContent == null) {
           throw new IMallException("No corresponding information");
       }
        return tbPanelContent;
    }

    @Override
    public boolean update(TbPanelContent tbPanelContent) {
        if (tbPanelContent.getPic_url().equals("") && tbPanelContent.getPic_url2().equals("") && tbPanelContent.getPic_url3().equals("")) {
            TbItem tbItem = tbItemMapper.selectById(tbPanelContent.getProduct_id());
            String[] images = tbItem.getImage().split(",");
            for (int i = 0; i < images.length; i++) {
                if (i == 0) {
                    tbPanelContent.setPic_url(images[0]);
                }
                if (i == 1) {
                    tbPanelContent.setPic_url2(images[1]);
                }
                if (i == 2) {
                    tbPanelContent.setPic_url3(images[2]);
                }
            }
        }
        if (tbPanelContent.getPanel_id().equals("") || tbPanelContent.getProduct_id().equals("") || tbPanelContent.getPic_url().equals("")) {
            return false;
        }
        tbPanelContent.setUpdated(new Date());
        if (tbPanelContentMapper.updateById(tbPanelContent) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSortOrder(List<TbPanelContent> list) {
        for (int i = 0; i < list.size(); i++) {
            TbPanelContent tbPanelContent = list.get(i);
            tbPanelContent.setSort_order(i + 1);
            if (tbPanelContentMapper.updateById(tbPanelContent) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean del(Integer id) {
        return tbPanelContentMapper.deleteById(id) == 1;
    }

    private DataTablesResult getAllPanelContentCount() {
        long count = tbPanelContentMapper.selectCount(new QueryWrapper<>());
        DataTablesResult result = new DataTablesResult();
        result.setRecordsTotal((int) count);
        return result;
    }
}
