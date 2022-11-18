package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbItemCollection;
import com.binbini.imall.service.item.ItemCollectionClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/10:27
 * @Description:
 */
@RestController
public class ItemCollectionConsumerController {

    @Autowired
    private ItemCollectionClientService itemCollectionClientService;

    @PostMapping("/s/collection/like")
    public int collection(@RequestParam("userId") Integer userId,
                          @RequestParam("productId") Integer productId,
                          @RequestParam("status") Integer status) {
        return itemCollectionClientService.collection(userId, productId, status);
    }

    @GetMapping("/s/collection/list")
    public List<Integer> getCollectionList(@RequestParam("userId") Integer userId) {
        return itemCollectionClientService.getCollectionList(userId);
    }

    @GetMapping("/s/collection/list/top")
    public List<TbItemCollection> getTopCollectionItemList() {
        return itemCollectionClientService.getTopCollectionItemList();
    }

}
