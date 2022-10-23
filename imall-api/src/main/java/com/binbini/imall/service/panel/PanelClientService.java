package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.vo.PanelVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/15:55
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-PANEL", fallbackFactory = PanelClientFallbackFactory.class)
public interface PanelClientService {

    @PostMapping("/panel/create")
    public int createPanel(@RequestBody TbPanel tbPanel);

    @GetMapping("/panel/get/page")
    public List<PanelVo> findAllPanel();

    @GetMapping("/panel/get")
    public TbPanel findPanelById(@RequestParam("id") Integer id);

    @PostMapping("/panel/update")
    public boolean update(@RequestBody TbPanel tbPanel);

    @PostMapping("/panel/update/sort")
    public boolean updateSortOrder(@RequestBody List<TbPanel> list);

    @PostMapping("/panel/del")
    public boolean del(@RequestParam("id") Integer id);

}
