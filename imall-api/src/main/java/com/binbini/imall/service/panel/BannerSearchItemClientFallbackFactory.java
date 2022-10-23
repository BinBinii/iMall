package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbBannerSearchItem;
import com.binbini.imall.vo.BannerSearchItemVo;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/15:54
 * @Description:
 */
public class BannerSearchItemClientFallbackFactory implements FallbackFactory {
    @Override
    public BannerSearchItemClientService create(Throwable throwable) {
        return new BannerSearchItemClientService() {
            @Override
            public int createBannerSearchItem(TbBannerSearchItem tbBannerSearchItem) {
                return 0;
            }

            @Override
            public List<BannerSearchItemVo> findAllBannerSearchItemBySearchId(Integer search_id) {
                return null;
            }

            @Override
            public TbBannerSearchItem findBannerSearchItemById(Integer id) {
                return null;
            }

            @Override
            public boolean update(TbBannerSearchItem tbBannerSearchItem) {
                return false;
            }

            @Override
            public boolean updateSort(List<Integer> ids) {
                return false;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
