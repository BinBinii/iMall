package com.binbini.imall.controller;

import com.binbini.imall.dto.ItemDescDto;
import com.binbini.imall.service.item.ItemDescClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:47
 * @Description:
 */
@RestController
public class ItemDescConsumerController {

    @Autowired
    private ItemDescClientService itemDescClientService;

    @PostMapping("/s/desc/create")
    public int createItemDesc(@RequestBody ItemDescDto itemDescDto) {
        return itemDescClientService.createItemDesc(itemDescDto);
    }

    @PostMapping("/s/desc/update")
    public boolean update(@RequestBody ItemDescDto itemDescDto) {
        return itemDescClientService.update(itemDescDto);
    }

    @PostMapping("/s/desc/del/{item_id}")
    public boolean del(@PathVariable("item_id") Integer item_id) {
        return itemDescClientService.del(item_id);
    }

}
