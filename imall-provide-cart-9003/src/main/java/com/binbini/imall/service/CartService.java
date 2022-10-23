package com.binbini.imall.service;

import com.binbini.imall.vo.CartVo;
import org.springframework.stereotype.Service;

/**
 * @Author: BinBin
 * @Date: 2022/09/23/09:41
 * @Description:
 */
@Service
public interface CartService {

    /**
     * 购物车内新增商品
     * @param userId
     * @param itemId
     * @param num
     * @return
     */
    boolean addCartItem(Integer userId, Integer itemId, Integer num);

    /**
     * 根据用户ID获取购物车
     * @param userId
     * @return
     */
    CartVo getCartList(Integer userId);

    /**
     * 更新购物车
     * @param userId
     * @param itemId
     * @param num
     * @param checked
     * @return
     */
    boolean updateCart(Integer userId, Integer itemId, Integer num, Boolean checked);

    /**
     * 全选/取消全选
     * @param userId
     * @param checked
     * @return
     */
    boolean checkAll(Integer userId, Boolean checked);

    /**
     * 购物车内删除商品
     * @param userId
     * @param itemId
     * @return
     */
    boolean delCartItem(Integer userId, Integer itemId);

}
