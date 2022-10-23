package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbBannerSearch;
import com.binbini.imall.vo.BannerSearchVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/15:53
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-PANEL", fallbackFactory = BannerSearchClientFallbackFactory.class)
public interface BannerSearchClientService {

    @PostMapping("/banner/create")
    public int createBannerSearch(@RequestBody TbBannerSearch tbBannerSearch);

    @GetMapping("/banner/get/all")
    public List<BannerSearchVo> findAllBannerSearch();

    @GetMapping("/banner/get")
    public TbBannerSearch findBannerSearchById(@RequestParam("id") Integer id);

    @PostMapping("/banner/update")
    public boolean update(@RequestBody TbBannerSearch tbBannerSearch);

    @PostMapping("/banner/del")
    public boolean del(@RequestParam("id") Integer id);

}
