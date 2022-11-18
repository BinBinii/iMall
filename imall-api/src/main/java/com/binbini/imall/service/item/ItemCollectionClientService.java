package com.binbini.imall.service.item;

import com.binbini.imall.pojo.TbItemCollection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/10:24
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-ITEM", fallbackFactory = ItemCollectionClientFallbackFactory.class)
public interface ItemCollectionClientService {
    @PostMapping("/item/collection/like")
    public int collection(@RequestParam("userId") Integer userId,
                          @RequestParam("productId") Integer productId,
                          @RequestParam("status") Integer status);

    @GetMapping("/item/collection/list")
    public List<Integer> getCollectionList(@RequestParam("userId") Integer userId);

    @GetMapping("/item/collection/list/top")
    public List<TbItemCollection> getTopCollectionItemList();
}
