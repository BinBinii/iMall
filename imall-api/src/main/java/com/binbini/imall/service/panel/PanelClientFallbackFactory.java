package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.vo.PanelVo;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/15:55
 * @Description:
 */
public class PanelClientFallbackFactory implements FallbackFactory {
    @Override
    public PanelClientService create(Throwable throwable) {
        return new PanelClientService() {
            @Override
            public int createPanel(TbPanel tbPanel) {
                return 0;
            }

            @Override
            public List<PanelVo> findAllPanel() {
                return null;
            }

            @Override
            public List<TbPanel> getTbPanelList() {
                return null;
            }

            @Override
            public TbPanel findPanelById(Integer id) {
                return null;
            }

            @Override
            public boolean update(TbPanel tbPanel) {
                return false;
            }

            @Override
            public boolean updateSortOrder(List<TbPanel> list) {
                return false;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
