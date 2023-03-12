package com.binbini.imall.service.impl;

import com.binbini.imall.service.CartService;
import com.binbini.imall.utils.RedisUtil;
import com.binbini.imall.vo.CartProduct;
import com.binbini.imall.vo.CartVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: BinBin
 * @Date: 2022/09/23/09:42
 * @Description:
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${cart.pre}")
    private String CART_PRE;

    @Override
    public boolean addCartItem(Integer userId, Integer itemId, Integer num, Integer color, Integer version) {
        // 查询redis内是否存在
        boolean exists = redisUtil.hasKey(CART_PRE + ":" + userId + "");
        // 如存在则增加商品数量
        if (exists) {
            String json = String.valueOf(redisUtil.get(CART_PRE + ":" + userId + ""));
            if (json != null) {
                CartVo cartVo = new Gson().fromJson(json, CartVo.class);
                List<CartProduct> cartProductList = cartVo.getProductList();
                boolean newCart = true;
                for (CartProduct item:cartProductList) {
                    if (item.getItem_id().equals(itemId) && item.getColor().equals(color) && item.getVersion().equals(version)) {
                        item.setNum(item.getNum() + num);
                        newCart = false;
                        break;
                    }
                }
                if (newCart) {
                    CartProduct cartProduct = new CartProduct();
                    cartProduct.setItem_id(itemId)
                            .setNum(num)
                            .setVersion(version)
                            .setColor(color)
                            .setChecked(true);
                    cartProductList.add(cartProduct);
                }
                redisUtil.set(CART_PRE + ":" + userId + "", new Gson().toJson(cartVo));
                return true;
            } else {
                return false;
            }
        }
        // 如果不存在则新增购物车
        List<CartProduct> cartProductList = new ArrayList<>();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setItem_id(itemId)
                .setNum(num)
                .setVersion(version)
                .setColor(color)
                .setChecked(true);
        cartProductList.add(cartProduct);
        CartVo cartVo = new CartVo();
        cartVo.setUser_id(userId)
                .setProductList(cartProductList);
        redisTemplate.opsForValue().set(CART_PRE + ":" + userId + "", new Gson().toJson(cartVo), 30, TimeUnit.DAYS);
//        redisUtil.set(CART_PRE + ":" + userId + "", new Gson().toJson(cartVo));
        return true;
    }

    @Override
    public CartVo getCartList(Integer userId) {
        String json = String.valueOf(redisUtil.get(CART_PRE + ":" + userId + ""));
        CartVo cartVo = new CartVo();
        if (json != null) {
            cartVo = new Gson().fromJson(json, CartVo.class);
        }
        return cartVo;
    }

    @Override
    public boolean updateCart(Integer userId, Integer itemId, Integer num, Integer color, Integer version, Boolean checked) {
        String json = String.valueOf(redisUtil.get(CART_PRE + ":" + userId + ""));
        if (json == null) {
            return false;
        }
        CartVo cartVo = new Gson().fromJson(json, CartVo.class);
        List<CartProduct> cartProductList = cartVo.getProductList();
        for (CartProduct item:cartProductList) {
            if (item.getItem_id().equals(itemId) && item.getColor().equals(color) && item.getVersion().equals(version)) {
                item.setNum(num)
                        .setColor(color)
                        .setVersion(version)
                        .setChecked(checked);
            }
        }
        redisUtil.set(CART_PRE + ":" + userId + "", new Gson().toJson(cartVo));
        return true;
    }

    @Override
    public boolean checkAll(Integer userId, Boolean checked) {
        String json = String.valueOf(redisUtil.get(CART_PRE + ":" + userId + ""));
        if (json == null) {
            return false;
        }
        CartVo cartVo = new Gson().fromJson(json, CartVo.class);
        List<CartProduct> cartProductList = cartVo.getProductList();
        for (CartProduct item:cartProductList) {
            item.setChecked(checked);
        }
        redisUtil.set(CART_PRE + ":" + userId + "", new Gson().toJson(cartVo));
        return true;
    }

    @Override
    public boolean delCartItem(Integer userId, Integer itemId) {
        String json = String.valueOf(redisUtil.get(CART_PRE + ":" + userId + ""));
        if (json == null) {
            return false;
        }
        CartVo cartVo = new Gson().fromJson(json, CartVo.class);
        List<CartProduct> cartProductList = cartVo.getProductList();
        for (CartProduct item:cartProductList) {
            if (item.getItem_id().equals(itemId)) {
                cartProductList.remove(item);
                break;
            }
        }
        redisUtil.set(CART_PRE + ":" + userId + "", new Gson().toJson(cartVo));
        return true;
    }
}
