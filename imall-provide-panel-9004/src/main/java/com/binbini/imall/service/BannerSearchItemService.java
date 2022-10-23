package com.binbini.imall.service;

import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.vo.BannerSearchItemVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/09:38
 * @Description:
 */
@Service
public interface BannerSearchItemService {
    /**
     * 创建Banner搜索item
     * @param tbBannerSearchItem
     * @return
     */
    int createBannerSearchItem(TbBannerSearchItem tbBannerSearchItem);

    /**
     * 根据search_id查询Banner搜索item
     * @return
     */
    List<BannerSearchItemVo> findAllBannerSearchItemBySearchId(Integer search_id);

    /**
     * 根据ID查找Banner搜索item
     * @param id
     * @return
     */
    TbBannerSearchItem findBannerSearchItemById(Integer id);

    /**
     * 更新Banner搜索item
     * @param tbBannerSearchItem
     * @return
     */
    boolean update(TbBannerSearchItem tbBannerSearchItem);

    /**
     * 更新Banner搜索item的排序
     * @param ids
     * @return
     */
    boolean updateSort(List<Integer> ids);

    /**
     * 删除Banner搜索item
     * @param id
     * @return
     */
    boolean del(Integer id);
}
