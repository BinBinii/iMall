package com.binbini.imall.service;

import com.binbini.imall.dto.ItemCatDto;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.ItemCatVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/08:50
 * @Description:
 */
@Service
public interface ItemCatService {

    /**
     * 创建商品分类
     * @param itemCatDto
     * @return
     */
    int createItemCat(ItemCatDto itemCatDto);

    /**
     * 多条件分页查询商品分类
     * @param start
     * @param length
     * @param search
     * @param parent_id
     * @param minDate
     * @param maxDate
     * @return
     */
    DataTablesResult findItemCatSearchPage(int start, int length, String search, int parent_id, String minDate, String maxDate);

    /**
     * 查找所有父分类
     * @return
     */
    List<TbItemCat> findItemCatByAllParent();

    /**
     * 所有商品 ItemCat
     * @return
     */
    List<ItemCatVo> cascaderItemCat();

    /**
     * 下拉选择 ItemCat
     * @return
     */
    List<ItemCatVo> choiceItemCat();

    /**
     * 根据ID查找商品分类
     * @param id
     * @return
     */
    TbItemCat findItemCatById(Integer id);

    /**
     * 根据Parent_id查找商品分类
     * @param parent_id
     * @return
     */
    List<TbItemCat> findItemCatsByParent_id(Integer parent_id);

    /**
     * 更新商品分类
     * @param itemCatDto
     * @return
     */
    boolean update(ItemCatDto itemCatDto);

    /**
     * 更新商品分类排序
     * @param list
     * @return
     */
    boolean updateSortOrder(List<TbItemCat> list);

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    boolean del(Integer id);

}
