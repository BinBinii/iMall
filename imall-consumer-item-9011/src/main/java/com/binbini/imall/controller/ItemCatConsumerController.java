package com.binbini.imall.controller;

import com.binbini.imall.dto.ItemCatDto;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.service.item.ItemCatClientService;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.ItemCatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:47
 * @Description:
 */
@RestController
public class ItemCatConsumerController {

    @Autowired
    private ItemCatClientService itemCatClientService;

    @PostMapping("/s/cat/create")
    public int createItemCat(@RequestBody ItemCatDto itemCatDto) {
        return itemCatClientService.createItemCat(itemCatDto);
    }

    @GetMapping("/s/cat/get/page")
    public DataTablesResult findItemCatSearchPage(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam("search") String search,
                                                  @RequestParam("parent_id") int parent_id,
                                                  @RequestParam("minDate") String minDate,
                                                  @RequestParam("maxDate") String maxDate) {
        return itemCatClientService.findItemCatSearchPage(start, length, search, parent_id, minDate, maxDate);
    }

    @GetMapping("/s/cat/get/list/parent")
    public List<TbItemCat> findItemCatByAllParent() {
        return itemCatClientService.findItemCatByAllParent();
    }

    @GetMapping("/s/cat/get/list/cascader")
    public List<ItemCatVo> cascaderItemCat() {
        return itemCatClientService.cascaderItemCat();
    }

    @GetMapping("/s/cat/get/list/choice")
    public List<ItemCatVo> choiceItemCat() {
        return itemCatClientService.choiceItemCat();
    }

    @GetMapping("/s/cat/get/one")
    public TbItemCat findItemCatById(@RequestParam("id") Integer id) {
        return itemCatClientService.findItemCatById(id);
    }

    @GetMapping("/s/cat/get/list")
    public List<TbItemCat> findItemCatsByParent_id(@RequestParam("parent_id") Integer parent_id) {
        return itemCatClientService.findItemCatsByParent_id(parent_id);
    }

    @PostMapping("/s/cat/update")
    public boolean update(@RequestBody ItemCatDto itemCatDto) {
        return itemCatClientService.update(itemCatDto);
    }

    @PostMapping("/s/cat/update/sort")
    public boolean updateSortOrder(@RequestBody List<TbItemCat> list) {
        return itemCatClientService.updateSortOrder(list);
    }

    @PostMapping("/s/cat/del")
    public boolean del(@RequestParam("id") Integer id) {
        return itemCatClientService.del(id);
    }

}
