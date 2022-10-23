package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.service.BannerSearchItemService;
import com.binbini.imall.vo.BannerSearchItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/15:38
 * @Description:
 */
@RestController
@RequestMapping(value = "/banner/item/")
public class BannerSearchItemController {

    @Autowired
    private BannerSearchItemService bannerSearchItemService;

    @PostMapping("create")
    public int createBannerSearchItem(@RequestBody TbBannerSearchItem tbBannerSearchItem) {
        return bannerSearchItemService.createBannerSearchItem(tbBannerSearchItem);
    }

    @GetMapping("get/all")
    public List<BannerSearchItemVo> findAllBannerSearchItemBySearchId(@RequestParam("search_id") Integer search_id) {
        return bannerSearchItemService.findAllBannerSearchItemBySearchId(search_id);
    }

    @GetMapping("get")
    public TbBannerSearchItem findBannerSearchItemById(@RequestParam("id") Integer id) {
        return bannerSearchItemService.findBannerSearchItemById(id);
    }

    @PostMapping("update")
    public boolean update(@RequestBody TbBannerSearchItem tbBannerSearchItem) {
        return bannerSearchItemService.update(tbBannerSearchItem);
    }

    @PostMapping("update/sort")
    public boolean updateSort(@RequestBody List<Integer> ids) {
        return bannerSearchItemService.updateSort(ids);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return bannerSearchItemService.del(id);
    }

}
