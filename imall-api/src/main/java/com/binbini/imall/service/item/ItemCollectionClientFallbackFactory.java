package com.binbini.imall.service.item;

import com.binbini.imall.pojo.TbItemCollection;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/10:25
 * @Description:
 */
public class ItemCollectionClientFallbackFactory implements FallbackFactory {
    @Override
    public ItemCollectionClientService create(Throwable throwable) {
        return new ItemCollectionClientService() {
            @Override
            public int collection(Integer userId, Integer productId, Integer status) {
                return 0;
            }

            @Override
            public List<Integer> getCollectionList(Integer userId) {
                return null;
            }

            @Override
            public List<TbItemCollection> getTopCollectionItemList() {
                return null;
            }
        };
    }
}
