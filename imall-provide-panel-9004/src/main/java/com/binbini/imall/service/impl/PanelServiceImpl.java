package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.mapper.TbPanelContentMapper;
import com.binbini.imall.mapper.TbPanelMapper;
import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.service.PanelService;
import com.binbini.imall.vo.PanelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/11:11
 * @Description:
 */
@Service
public class PanelServiceImpl implements PanelService {

    @Autowired
    private TbPanelMapper tbPanelMapper;
    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;

    @Override
    public int createPanel(TbPanel tbPanel) {
        if (tbPanel.getName().equals("") || tbPanel.getType().equals("") || tbPanel.getSort_order().equals("")) {
            return 0;
        }
        if (tbPanel.getLimit_num().equals("")) {
            tbPanel.setLimit_num(9);
        }
        tbPanel.setStatus(1)
            .setCreated(new Date())
            .setUpdated(new Date());
        if (tbPanelMapper.insert(tbPanel) != 1) {
            return  -1;
        }
        return 1;
    }

    @Override
    public List<PanelVo> findAllPanel() {
        List<PanelVo> result = new ArrayList<>();
        QueryWrapper<TbPanel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort_order");
        List<TbPanel> tbPanels = tbPanelMapper.selectList(queryWrapper);
        for (TbPanel tbPanel:tbPanels) {
            PanelVo panelVo = new PanelVo();
            panelVo.setId(tbPanel.getId())
                    .setName(tbPanel.getName())
                    .setType(tbPanel.getType())
                    .setSort_order(tbPanel.getSort_order())
                    .setLimit_num(tbPanel.getLimit_num())
                    .setStatus(tbPanel.getStatus())
                    .setRemark(tbPanel.getRemark());
            QueryWrapper<TbPanelContent> tbPanelContentQueryWrapper = new QueryWrapper<>();
            tbPanelContentQueryWrapper.eq("panel_id", tbPanel.getId());
            tbPanelContentQueryWrapper.orderByAsc("sort_order");
            List<TbPanelContent> tbPanelContents = tbPanelContentMapper.selectList(tbPanelContentQueryWrapper);
            panelVo.setList(tbPanelContents);
            result.add(panelVo);
        }
        return result;
    }

    @Override
    public List<TbPanel> getTbPanelList() {
        return tbPanelMapper.selectList(new QueryWrapper<TbPanel>().orderByAsc("sort_order"));
    }

    @Override
    public TbPanel findPanelById(Integer id) {
        return tbPanelMapper.selectById(id);
    }

    @Override
    public boolean update(TbPanel tbPanel) {
        if (tbPanel.getName().equals("") || tbPanel.getType().equals("") || tbPanel.getSort_order().equals("") || tbPanel.getStatus().equals("")) {
            return false;
        }
        if (tbPanel.getLimit_num().equals("")) {
            tbPanel.setLimit_num(9);
        }
        tbPanel.setUpdated(new Date());
        if (tbPanelMapper.updateById(tbPanel) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSortOrder(List<TbPanel> list) {
        for (int i = 0; i < list.size(); i++) {
            TbPanel tbPanel = list.get(i);
            tbPanel.setSort_order(i + 1);
            if (tbPanelMapper.updateById(tbPanel) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean del(Integer id) {
        return tbPanelMapper.deleteById(id) == 1;
    }
}
