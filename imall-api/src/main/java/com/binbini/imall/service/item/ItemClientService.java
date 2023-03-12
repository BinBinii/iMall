package com.binbini.imall.service.item;

import com.binbini.imall.dto.ItemDto;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:48
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-ITEM", fallbackFactory = ItemClientFallbackFactory.class)
public interface ItemClientService {

    @PostMapping("/item/create")
    public int createItem(@RequestBody ItemDto itemDto);

    @GetMapping("/item/get/page")
    public DataTablesResult findItemSearchPage(@RequestParam("start") int start,
                                               @RequestParam("length") int length,
                                               @RequestParam("search") String search,
                                               @RequestParam("minDate") String minDate,
                                               @RequestParam("maxDate") String maxDate);

    @GetMapping("/item/get/page/cid")
    public DataTablesResult findItemSearchPageFromCid(@RequestParam("start") int start,
                                                      @RequestParam("length") int length,
                                                      @RequestParam("cid") String cid,
                                                      @RequestParam("orderCol") String orderCol,
                                                      @RequestParam("orderSort") String orderSort);

    @GetMapping("/item/get")
    public TbItem findItemById(@RequestParam("id") Integer id);

    @PostMapping("/item/update")
    public boolean update(@RequestBody ItemDto itemDto);

    @PostMapping("/item/del")
    public boolean del(@RequestParam("id") Integer id);

}
