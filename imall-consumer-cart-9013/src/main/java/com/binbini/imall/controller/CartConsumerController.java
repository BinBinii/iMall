package com.binbini.imall.controller;

import com.binbini.imall.service.cart.CartClientService;
import com.binbini.imall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: BinBin
 * @Date: 2022/09/26/18:07
 * @Description:
 */
@RestController
public class CartConsumerController {

    @Autowired
    private CartClientService cartClientService;

    @PostMapping("/s/add")
    public boolean addCartItem(@RequestParam("userId") Integer userId,
                               @RequestParam("itemId") Integer itemId,
                               @RequestParam("num") Integer num) {
        return cartClientService.addCartItem(userId, itemId, num);
    }

    @GetMapping("/s/list")
    public CartVo getCartList(@RequestParam("userId") Integer userId) {
        return cartClientService.getCartList(userId);
    }

    @PostMapping("/s/update")
    public boolean updateCart(@RequestParam("userId") Integer userId,
                              @RequestParam("itemId") Integer itemId,
                              @RequestParam("num") Integer num,
                              @RequestParam("checked") Boolean checked) {
        return cartClientService.updateCart(userId, itemId, num, checked);
    }

    @PostMapping("/s/check/all")
    public boolean checkAll(@RequestParam("userId") Integer userId,
                            @RequestParam("checked") Boolean checked) {
        return cartClientService.checkAll(userId, checked);
    }

    @PostMapping("/s/del")
    public boolean delCartItem(@RequestParam("userId") Integer userId,
                               @RequestParam("itemId") Integer itemId) {
        return cartClientService.delCartItem(userId, itemId);
    }

}
