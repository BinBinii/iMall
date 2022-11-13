package com.binbini.imall.service.cart;

import com.binbini.imall.vo.CartVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: BinBin
 * @Date: 2022/09/26/18:08
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-CART", fallbackFactory = CartClientFallbackFactory.class)
public interface CartClientService {

    @PostMapping("/cart/add")
    public boolean addCartItem(@RequestParam("userId") Integer userId,
                               @RequestParam("itemId") Integer itemId,
                               @RequestParam("num") Integer num,
                               @RequestParam("color") Integer color,
                               @RequestParam("version") Integer version);

    @GetMapping("/cart/list")
    public CartVo getCartList(@RequestParam("userId") Integer userId);

    @PostMapping("cart/update")
    public boolean updateCart(@RequestParam("userId") Integer userId,
                              @RequestParam("itemId") Integer itemId,
                              @RequestParam("num") Integer num,
                              @RequestParam("color") Integer color,
                              @RequestParam("version") Integer version,
                              @RequestParam("checked") Boolean checked);

    @PostMapping("/cart/check/all")
    public boolean checkAll(@RequestParam("userId") Integer userId,
                            @RequestParam("checked") Boolean checked);

    @PostMapping("/cart/del")
    public boolean delCartItem(@RequestParam("userId") Integer userId,
                               @RequestParam("itemId") Integer itemId);

}
