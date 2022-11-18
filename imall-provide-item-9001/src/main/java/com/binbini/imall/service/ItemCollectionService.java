package com.binbini.imall.service;

import com.binbini.imall.pojo.TbItemCollection;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/13/15:52
 * @Description:
 */
@Service
public interface ItemCollectionService {
    /**
     * 收藏商品
     * @param userId
     * @param productId
     * @param status
     * @return
     */
    int collection(Integer userId,Integer productId, Integer status);

    /**
     * 获取用户收藏商品列表
     * @param userId
     * @return
     */
    List<Integer> getCollectionList(Integer userId);

    /**
     * 获取收藏最高的商品列表
     * @return
     */
    List<TbItemCollection> getTopCollectionItemList();

    /**
     * 导入
     * @return
     */
    boolean importAll();

}
