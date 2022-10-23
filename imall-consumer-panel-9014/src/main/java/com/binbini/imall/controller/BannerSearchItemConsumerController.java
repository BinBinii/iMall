package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.service.panel.BannerSearchItemClientService;
import com.binbini.imall.vo.BannerSearchItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/16:07
 * @Description:
 */
@RestController
public class BannerSearchItemConsumerController {

    @Autowired
    private BannerSearchItemClientService bannerSearchItemClientService;

    @PostMapping("/s/banner/item/create")
    public int createBannerSearchItem(@RequestBody TbBannerSearchItem tbBannerSearchItem) {
        return bannerSearchItemClientService.createBannerSearchItem(tbBannerSearchItem);
    }

    @GetMapping("/s/banner/item/get/all")
    public List<BannerSearchItemVo> findAllBannerSearchItemBySearchId(@RequestParam(value = "search_id", defaultValue = "1") Integer search_id) {
        return bannerSearchItemClientService.findAllBannerSearchItemBySearchId(search_id);
    }

    @GetMapping("/s/banner/item/get")
    public TbBannerSearchItem findBannerSearchItemById(@RequestParam("id") Integer id) {
        return bannerSearchItemClientService.findBannerSearchItemById(id);
    }

    @PostMapping("/s/banner/item/update")
    public boolean update(@RequestBody TbBannerSearchItem tbBannerSearchItem) {
        return bannerSearchItemClientService.update(tbBannerSearchItem);
    }

    @PostMapping("/s/banner/item/update/sort")
    public boolean updateSort(@RequestBody List<Integer> ids) {
        return bannerSearchItemClientService.updateSort(ids);
    }

    @PostMapping("/s/banner/item/del")
    public boolean del(@RequestParam("id") Integer id) {
        return bannerSearchItemClientService.del(id);
    }

}
