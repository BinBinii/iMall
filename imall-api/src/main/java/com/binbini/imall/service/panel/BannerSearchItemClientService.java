package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.vo.BannerSearchItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/15:54
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-PANEL", fallbackFactory = BannerSearchClientFallbackFactory.class)
public interface BannerSearchItemClientService {

    @PostMapping("/banner/item/create")
    public int createBannerSearchItem(@RequestBody TbBannerSearchItem tbBannerSearchItem);

    @GetMapping("/banner/item/get/all")
    public List<BannerSearchItemVo> findAllBannerSearchItemBySearchId(@RequestParam("search_id") Integer search_id);

    @GetMapping("/banner/item/get")
    public TbBannerSearchItem findBannerSearchItemById(@RequestParam("id") Integer id);

    @PostMapping("/banner/item/update")
    public boolean update(@RequestBody TbBannerSearchItem tbBannerSearchItem);

    @PostMapping("/banner/item/update/sort")
    public boolean updateSort(@RequestBody List<Integer> ids);

    @PostMapping("/banner/item/del")
    public boolean del(@RequestParam("id") Integer id);

}
