package com.binbini.imall.service;

import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/11:11
 * @Description:
 */
@Service
public interface PanelContentService {

    /**
     * 创建板块内容
     * @param tbPanelContent
     * @return
     */
    int createPanelContent(TbPanelContent tbPanelContent);

    /**
     * 多条件分页查询板块内容
     * @param start
     * @param length
     * @param search
     * @param minDate
     * @param maxDate
     * @return
     */
    DataTablesResult findPanelContentSearchPage(int start, int length, String search, String minDate, String maxDate);

    /**
     * 根据ID查找板块
     * @param id
     * @return
     */
    TbPanelContent findPanelContentById(Integer id);

    /**
     * 更新板块内容
     * @param tbPanelContent
     * @return
     */
    boolean update(TbPanelContent tbPanelContent);

    /**
     * 更新板块内容排序
     * @param list
     * @return
     */
    boolean updateSortOrder(List<TbPanelContent> list);

    /**
     * 删除板块内容
     * @param id
     * @return
     */
    boolean del(Integer id);

}
