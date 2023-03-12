package com.binbini.imall.service;

import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.vo.PanelVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/11:11
 * @Description:
 */
@Service
public interface PanelService {

    /**
     * 创建板块
     * @param tbPanel
     * @return
     */
    int createPanel(TbPanel tbPanel);

    /**
     * 查询所有板块
     * @return
     */
    List<PanelVo> findAllPanel();

    List<TbPanel> getTbPanelList();

    /**
     * 根据ID查找板块
     * @param id
     * @return
     */
    TbPanel findPanelById(Integer id);

    /**
     * 更新板块
     * @param tbPanel
     * @return
     */
    boolean update(TbPanel tbPanel);

    /**
     * 更新板块排序
     * @param list
     * @return
     */
    boolean updateSortOrder(List<TbPanel> list);

    /**
     * 删除板块
     * @param id
     * @return
     */
    boolean del(Integer id);

}
