package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.vo.DataTablesResult;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/15:56
 * @Description:
 */
public class PanelContentClientFallbackFactory implements FallbackFactory {
    @Override
    public PanelContentClientService create(Throwable throwable) {
        return new PanelContentClientService() {
            @Override
            public int createPanelContent(TbPanelContent tbPanelContent) {
                return 0;
            }

            @Override
            public DataTablesResult findPanelContentSearchPage(int start, int length, String search, String minDate, String maxDate) {
                return null;
            }

            @Override
            public TbPanelContent findPanelContentById(Integer id) {
                return null;
            }

            @Override
            public boolean update(TbPanelContent tbPanelContent) {
                return false;
            }

            @Override
            public boolean updateSortOrder(List<TbPanelContent> list) {
                return false;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
