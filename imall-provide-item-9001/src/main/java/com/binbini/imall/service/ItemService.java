package com.binbini.imall.service;

import com.binbini.imall.dto.ItemDto;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.stereotype.Service;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/18:57
 * @Description:
 */
@Service
public interface ItemService {

    /**
     * 创建商品
     * @param itemDto
     * @return
     */
    int createItem(ItemDto itemDto);

    /**
     * 多条件分页查询商品
     * @param start
     * @param length
     * @param search
     * @param minDate
     * @param maxDate
     * @return
     */
    DataTablesResult findItemSearchPage(int start, int length, String search, String minDate, String maxDate);

    /**
     * 根据Cid多条件分页查询商品
     * @param start
     * @param length
     * @param cid
     * @param orderCol
     * @param orderSort
     * @return
     */
    DataTablesResult findItemSearchPageFromCid(int start, int length, int cid, String orderCol, String orderSort);

    /**
     * 根据ID查找商品
     * @param id
     * @return
     */
    TbItem findItemById(Integer id);

    /**
     * 更新商品信息
     * @param itemDto
     * @return
     */
    boolean update(ItemDto itemDto);

    /**
     * 删除商品
     * @param id
     * @return
     */
    boolean del(Integer id);

}
