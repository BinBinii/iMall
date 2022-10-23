package com.binbini.imall.controller;

import com.binbini.imall.dto.ItemDescDto;
import com.binbini.imall.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/10:39
 * @Description:
 */
@RestController
@RequestMapping(value = "/item/desc/")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    @PostMapping("create")
    public int createItemDesc(@RequestBody ItemDescDto itemDescDto) {
        return itemDescService.createItemDesc(itemDescDto);
    }

    @PostMapping("update")
    public boolean update(@RequestBody ItemDescDto itemDescDto) {
        return itemDescService.update(itemDescDto);
    }

    @PostMapping("del/{item_id}")
    public boolean del(@PathVariable("item_id") Integer item_id) {
        return itemDescService.del(item_id);
    }

}
