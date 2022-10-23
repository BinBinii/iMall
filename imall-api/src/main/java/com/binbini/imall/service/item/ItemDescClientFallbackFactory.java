package com.binbini.imall.service.item;

import com.binbini.imall.dto.ItemDescDto;
import feign.hystrix.FallbackFactory;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:50
 * @Description:
 */
public class ItemDescClientFallbackFactory implements FallbackFactory {
    @Override
    public ItemDescClientService create(Throwable throwable) {
        return new ItemDescClientService() {
            @Override
            public int createItemDesc(ItemDescDto itemDescDto) {
                return 0;
            }

            @Override
            public boolean update(ItemDescDto itemDescDto) {
                return false;
            }

            @Override
            public boolean del(Integer item_id) {
                return false;
            }
        };
    }
}
