package com.binbini.imall.service.item;

import com.binbini.imall.dto.ItemDto;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.vo.DataTablesResult;
import feign.hystrix.FallbackFactory;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:48
 * @Description:
 */
public class ItemClientFallbackFactory implements FallbackFactory {
    @Override
    public ItemClientService create(Throwable throwable) {
        return new ItemClientService() {
            @Override
            public int createItem(ItemDto itemDto) {
                return 0;
            }

            @Override
            public DataTablesResult findItemSearchPage(int start, int length, String search, String minDate, String maxDate) {
                return null;
            }

            @Override
            public DataTablesResult findItemSearchPageFromCid(int start, int length, String cid, String orderCol, String orderSort) {
                return null;
            }

            @Override
            public TbItem findItemById(Integer id) {
                return new TbItem()
                        .setId(id)
                        .setTitle("id => " + id + " There is no corresponding information. The client provided degraded information. The service has been shut down.");
            }

            @Override
            public boolean update(ItemDto itemDto) {
                return false;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
