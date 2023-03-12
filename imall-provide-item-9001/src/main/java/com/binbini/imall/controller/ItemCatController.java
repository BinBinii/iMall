package com.binbini.imall.controller;

import com.binbini.imall.dto.ItemCatDto;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.service.ItemCatService;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.ItemCatVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/10:39
 * @Description:
 */
@RestController
@RequestMapping(value = "/item/cat/")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @PostMapping("create")
    public int createItemCat(@RequestBody ItemCatDto itemCatDto) {
        return itemCatService.createItemCat(itemCatDto);
    }

    @GetMapping("get/page")
    public DataTablesResult findItemCatSearchPage(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam("search") String search,
                                                  @RequestParam("parent_id") int parent_id,
                                                  @RequestParam("minDate") String minDate,
                                                  @RequestParam("maxDate") String maxDate) {
        return itemCatService.findItemCatSearchPage(start, length, search, parent_id, minDate, maxDate);
    }

    @GetMapping("get/list/parent")
    public List<TbItemCat> findItemCatByAllParent() {
        return itemCatService.findItemCatByAllParent();
    }

    @GetMapping("get/list/cascader")
    public List<ItemCatVo> cascaderItemCat() {
        return itemCatService.cascaderItemCat();
    }

    @GetMapping("get/list/choice")
    public List<ItemCatVo> choiceItemCat() {
        return itemCatService.choiceItemCat();
    }

    @GetMapping("get/one")
    @HystrixCommand(fallbackMethod = "hystrixGetTbItemCat")
    public TbItemCat findItemCatById(@RequestParam("id") Integer id) {
        return itemCatService.findItemCatById(id);
    }

    @GetMapping("get/list")
    public List<TbItemCat> findItemCatsByParent_id(@RequestParam("parent_id") Integer parent_id) {
        return itemCatService.findItemCatsByParent_id(parent_id);
    }

    @PostMapping("update")
    public boolean update(@RequestBody ItemCatDto itemCatDto) {
        return itemCatService.update(itemCatDto);
    }

    @PostMapping("update/sort")
    public boolean updateSortOrder(@RequestBody List<TbItemCat> list) {
        return itemCatService.updateSortOrder(list);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return itemCatService.del(id);
    }

    public TbItemCat hystrixGetTbItemCat(@RequestParam("id") Integer id) {
        return new TbItemCat()
                .setId(id)
                .setName("id => " + id + " No corresponding information, null -- @Hystrix");
    }

}
