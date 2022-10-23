package com.binbini.imall.service.item;

import com.binbini.imall.dto.ItemCatDto;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.ItemCatVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:49
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-ITEM", fallbackFactory = ItemCatClientFallbackFactory.class)
public interface ItemCatClientService {

    @PostMapping("/item/cat/create")
    public int createItemCat(@RequestBody ItemCatDto itemCatDto);

    @GetMapping("/item/cat/get/page")
    public DataTablesResult findItemCatSearchPage(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam("search") String search,
                                                  @RequestParam("parent_id") int parent_id,
                                                  @RequestParam("minDate") String minDate,
                                                  @RequestParam("maxDate") String maxDate);

    @GetMapping("/item/cat/get/list/parent")
    public List<TbItemCat> findItemCatByAllParent();

    @GetMapping("/item/cat/get/list/cascader")
    public List<ItemCatVo> cascaderItemCat();

    @GetMapping("/item/cat/get/one")
    public TbItemCat findItemCatById(@RequestParam("id") Integer id);

    @GetMapping("/item/cat/get/list")
    public List<TbItemCat> findItemCatsByParent_id(@RequestParam("parent_id") Integer parent_id);

    @PostMapping("/item/cat/update")
    public boolean update(@RequestBody ItemCatDto itemCatDto);

    @PostMapping("/item/cat/update/sort")
    public boolean updateSortOrder(@RequestBody List<TbItemCat> list);

    @PostMapping("/item/cat/del")
    public boolean del(@RequestParam("id") Integer id);

}
