package com.binbini.imall.service;

import com.binbini.imall.pojo.TbBannerSearch;
import com.binbini.imall.vo.BannerSearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/09:36
 * @Description:
 */
@Service
public interface BannerSearchService {
    /**
     * 创建Banner搜索
     * @param tbBannerSearch
     * @return
     */
    int createBannerSearch(TbBannerSearch tbBannerSearch);

    /**
     * 查询所有Banner搜索
     * @return
     */
    List<BannerSearchVo> findAllBannerSearch();

    /**
     * 根据ID查找Banner搜索
     * @param id
     * @return
     */
    TbBannerSearch findBannerSearchById(Integer id);

    /**
     * 更新Banner搜索
     * @param tbBannerSearch
     * @return
     */
    boolean update(TbBannerSearch tbBannerSearch);

    /**
     * 删除Banner搜索
     * @param id
     * @return
     */
    boolean del(Integer id);
}
