package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbBannerSearch;
import com.binbini.imall.service.BannerSearchService;
import com.binbini.imall.vo.BannerSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/15:38
 * @Description:
 */
@RestController
@RequestMapping(value = "/banner/")
public class BannerSearchController {

    @Autowired
    private BannerSearchService bannerSearchService;

    @PostMapping("create")
    public int createBannerSearch(@RequestBody TbBannerSearch tbBannerSearch) {
        return bannerSearchService.createBannerSearch(tbBannerSearch);
    }

    @GetMapping("get/all")
    public List<BannerSearchVo> findAllBannerSearch() {
        return bannerSearchService.findAllBannerSearch();
    }

    @GetMapping("get")
    public TbBannerSearch findBannerSearchById(@RequestParam("id") Integer id) {
        return bannerSearchService.findBannerSearchById(id);
    }

    @PostMapping("update")
    public boolean update(@RequestBody TbBannerSearch tbBannerSearch) {
        return bannerSearchService.update(tbBannerSearch);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return bannerSearchService.del(id);
    }

}
