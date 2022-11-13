package com.binbini.imall.service.cart;

import com.binbini.imall.vo.CartVo;
import feign.hystrix.FallbackFactory;

/**
 * @Author: BinBin
 * @Date: 2022/09/26/18:08
 * @Description:
 */
public class CartClientFallbackFactory implements FallbackFactory {
    @Override
    public CartClientService create(Throwable throwable) {
        return new CartClientService() {
            @Override
            public boolean addCartItem(Integer userId, Integer itemId, Integer num, Integer color, Integer version) {
                return false;
            }

            @Override
            public CartVo getCartList(Integer userId) {
                return new CartVo()
                        .setUser_id(userId)
                        .setProductList(null);
            }

            @Override
            public boolean updateCart(Integer userId, Integer itemId, Integer num, Integer color, Integer version, Boolean checked) {
                return false;
            }

            @Override
            public boolean checkAll(Integer userId, Boolean checked) {
                return false;
            }

            @Override
            public boolean delCartItem(Integer userId, Integer itemId) {
                return false;
            }
        };
    }
}
