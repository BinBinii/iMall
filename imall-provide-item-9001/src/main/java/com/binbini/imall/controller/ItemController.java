package com.binbini.imall.controller;

import com.binbini.imall.dto.ItemDto;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.service.ItemService;
import com.binbini.imall.vo.DataTablesResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/10:38
 * @Description:
 */
@RestController
@RequestMapping(value = "/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("create")
    public int createItem(@RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }

    @GetMapping("get/page")
    public DataTablesResult findItemSearchPage(@RequestParam("start") int start,
                                               @RequestParam("length") int length,
                                               @RequestParam("search") String search,
                                               @RequestParam("minDate") String minDate,
                                               @RequestParam("maxDate") String maxDate) {
        return itemService.findItemSearchPage(start, length, search, minDate, maxDate);
    }

    @GetMapping("get/page/cid")
    public DataTablesResult findItemSearchPageFromCid(@RequestParam("start") int start,
                                                      @RequestParam("length") int length,
                                                      @RequestParam("cid") int cid,
                                                      @RequestParam("orderCol") String orderCol,
                                                      @RequestParam("orderSort") String orderSort) {
        return itemService.findItemSearchPageFromCid(start, length, cid, orderCol, orderSort);
    }

    @GetMapping("get")
    @HystrixCommand(fallbackMethod = "hystrixGetTbItem")
    public TbItem findItemById(@RequestParam("id") Integer id) {
        return itemService.findItemById(id);
    }

    @PostMapping("update")
    public boolean update(@RequestBody ItemDto itemDto) {
        return itemService.update(itemDto);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return itemService.del(id);
    }

    public TbItem hystrixGetTbItem(@RequestParam("id") Integer id) {
        return new TbItem()
                .setId(id)
                .setTitle("id => " + id + " No corresponding information, null -- @Hystrix");
    }

}
