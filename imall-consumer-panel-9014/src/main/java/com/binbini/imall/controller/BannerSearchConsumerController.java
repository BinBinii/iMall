package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbBannerSearch;
import com.binbini.imall.service.panel.BannerSearchClientService;
import com.binbini.imall.vo.BannerSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/16:04
 * @Description:
 */
@RestController
public class BannerSearchConsumerController {

    @Autowired
    private BannerSearchClientService bannerSearchClientService;

    @PostMapping("/s/banner/create")
    public int createBannerSearch(@RequestBody TbBannerSearch tbBannerSearch) {
        return bannerSearchClientService.createBannerSearch(tbBannerSearch);
    }

    @GetMapping("/s/banner/get/all")
    public List<BannerSearchVo> findAllBannerSearch() {
        return bannerSearchClientService.findAllBannerSearch();
    }

    @GetMapping("/s/banner/get")
    public TbBannerSearch findBannerSearchById(@RequestParam("id") Integer id) {
        return bannerSearchClientService.findBannerSearchById(id);
    }

    @PostMapping("/s/banner/update")
    public boolean update(@RequestBody TbBannerSearch tbBannerSearch) {
        return bannerSearchClientService.update(tbBannerSearch);
    }

    @PostMapping("/s/banner/del")
    public boolean del(@RequestParam("id") Integer id) {
        return bannerSearchClientService.del(id);
    }


}
