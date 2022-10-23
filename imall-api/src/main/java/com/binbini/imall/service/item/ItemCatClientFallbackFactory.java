package com.binbini.imall.service.item;

import com.binbini.imall.dto.ItemCatDto;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.ItemCatVo;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/11:49
 * @Description:
 */
public class ItemCatClientFallbackFactory implements FallbackFactory {
    @Override
    public ItemCatClientService create(Throwable throwable) {
        return new ItemCatClientService() {
            @Override
            public int createItemCat(ItemCatDto itemCatDto) {
                return 0;
            }

            @Override
            public DataTablesResult findItemCatSearchPage(int start, int length, String search, int parent_id, String minDate, String maxDate) {
                return null;
            }

            @Override
            public List<TbItemCat> findItemCatByAllParent() {
                return null;
            }

            @Override
            public List<ItemCatVo> cascaderItemCat() {
                return null;
            }

            @Override
            public TbItemCat findItemCatById(Integer id) {
                return new TbItemCat()
                        .setId(id)
                        .setName("id => " + id + " There is no corresponding information. The client provided degraded information. The service has been shut down.");
            }

            @Override
            public List<TbItemCat> findItemCatsByParent_id(Integer parent_id) {
                return null;
            }

            @Override
            public boolean update(ItemCatDto itemCatDto) {
                return false;
            }

            @Override
            public boolean updateSortOrder(List<TbItemCat> list) {
                return false;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
