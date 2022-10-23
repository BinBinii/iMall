package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbBannerSearch;
import com.binbini.imall.vo.BannerSearchVo;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/05/15:53
 * @Description:
 */
public class BannerSearchClientFallbackFactory implements FallbackFactory {
    @Override
    public BannerSearchClientService create(Throwable throwable) {
        return new BannerSearchClientService() {
            @Override
            public int createBannerSearch(TbBannerSearch tbBannerSearch) {
                return 0;
            }

            @Override
            public List<BannerSearchVo> findAllBannerSearch() {
                return null;
            }

            @Override
            public TbBannerSearch findBannerSearchById(Integer id) {
                return null;
            }

            @Override
            public boolean update(TbBannerSearch tbBannerSearch) {
                return false;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
