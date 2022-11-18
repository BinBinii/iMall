package com.binbini.imall.controller;

import com.binbini.imall.dto.ItemDto;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.service.item.ItemClientService;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:46
 * @Description:
 */
@RestController
public class ItemConsumerController {

    @Autowired
    private ItemClientService itemClientService;

    @PostMapping("/s/create")
    public int createItem(@RequestBody ItemDto itemDto) {
        return itemClientService.createItem(itemDto);
    }

    @GetMapping("/s/get/page")
    public DataTablesResult findItemSearchPage(@RequestParam("start") int start,
                                               @RequestParam("length") int length,
                                               @RequestParam("search") String search,
                                               @RequestParam("minDate") String minDate,
                                               @RequestParam("maxDate") String maxDate) {
        return itemClientService.findItemSearchPage(start, length, search, minDate, maxDate);
    }

    @GetMapping("/s/get/page/cid")
    public DataTablesResult findItemSearchPageFromCid(@RequestParam("start") int start,
                                                      @RequestParam("length") int length,
                                                      @RequestParam("cid") int cid,
                                                      @RequestParam("orderCol") String orderCol,
                                                      @RequestParam("orderSort") String orderSort) {
        return itemClientService.findItemSearchPageFromCid(start, length, cid, orderCol, orderSort);
    }

    @GetMapping("/s/get")
    public TbItem findItemById(@RequestParam("id") Integer id) {
        return itemClientService.findItemById(id);
    }

    @PostMapping("/s/update")
    public boolean update(@RequestBody ItemDto itemDto) {
        return itemClientService.update(itemDto);
    }

    @PostMapping("/s/del")
    public boolean del(@RequestParam("id") Integer id) {
        return itemClientService.del(id);
    }

}
