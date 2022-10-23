package com.binbini.imall.controller;

import com.binbini.imall.service.CartService;
import com.binbini.imall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/23/09:42
 * @Description:
 */
@RestController
@RequestMapping(value = "/cart/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    public boolean addCartItem(@RequestParam("userId") Integer userId,
                               @RequestParam("itemId") Integer itemId,
                               @RequestParam("num") Integer num) {
        return cartService.addCartItem(userId, itemId, num);
    }

    @GetMapping("list")
    public CartVo getCartList(@RequestParam("userId") Integer userId) {
        return cartService.getCartList(userId);
    }

    @PostMapping("update")
    public boolean updateCart(@RequestParam("userId") Integer userId,
                              @RequestParam("itemId") Integer itemId,
                              @RequestParam("num") Integer num,
                              @RequestParam("checked") Boolean checked) {
        return cartService.updateCart(userId, itemId, num, checked);
    }

    @PostMapping("check/all")
    public boolean checkAll(@RequestParam("userId") Integer userId,
                            @RequestParam("checked") Boolean checked) {
        return cartService.checkAll(userId, checked);
    }

    @PostMapping("del")
    public boolean delCartItem(@RequestParam("userId") Integer userId,
                               @RequestParam("itemId") Integer itemId) {
        return cartService.delCartItem(userId, itemId);
    }

}
