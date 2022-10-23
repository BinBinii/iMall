package com.binbini.imall.service.item;

import com.binbini.imall.dto.ItemDescDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:49
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-ITEM", fallbackFactory = ItemDescClientFallbackFactory.class)
public interface ItemDescClientService {

    @PostMapping("/item/desc/create")
    public int createItemDesc(@RequestBody ItemDescDto itemDescDto);

    @PostMapping("/item/desc/update")
    public boolean update(@RequestBody ItemDescDto itemDescDto);

    @PostMapping("/item/desc/del/{item_id}")
    public boolean del(@PathVariable("item_id") Integer item_id);

}
