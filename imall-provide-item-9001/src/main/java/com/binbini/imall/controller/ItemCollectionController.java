package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbItemCollection;
import com.binbini.imall.service.ItemCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/10:16
 * @Description:
 */
@RestController
@RequestMapping(value = "/item/collection/")
public class ItemCollectionController {

    @Autowired
    private ItemCollectionService itemCollectionService;

    @PostMapping("like")
    public int collection(@RequestParam("userId") Integer userId,
                          @RequestParam("productId") Integer productId,
                          @RequestParam("status") Integer status) {
        return itemCollectionService.collection(userId, productId, status);
    }

    @GetMapping("list")
    public List<Integer> getCollectionList(@RequestParam("userId") Integer userId) {
        return itemCollectionService.getCollectionList(userId);
    }

    @GetMapping("list/top")
    public List<TbItemCollection> getTopCollectionItemList() {
        return itemCollectionService.getTopCollectionItemList();
    }

    @PostMapping("import/all")
    public boolean importAll() {
        return itemCollectionService.importAll();
    }

}
