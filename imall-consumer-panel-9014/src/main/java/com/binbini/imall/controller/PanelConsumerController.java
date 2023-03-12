package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.service.panel.PanelClientService;
import com.binbini.imall.vo.PanelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/15:55
 * @Description:
 */
@RestController
public class PanelConsumerController {

    @Autowired
    private PanelClientService panelClientService;

    @PostMapping("/s/panel/create")
    public int createPanel(@RequestBody TbPanel tbPanel) {
        return panelClientService.createPanel(tbPanel);
    }

    @GetMapping("/s/panel/get/page")
    public List<PanelVo> findAllPanel() {
        return panelClientService.findAllPanel();
    }

    @GetMapping("/s/panel/get/list")
    public List<TbPanel> getTbPanelList() {
        return panelClientService.getTbPanelList();
    }

    @GetMapping("/s/panel/get")
    public TbPanel findPanelById(@RequestParam("id") Integer id) {
        return panelClientService.findPanelById(id);
    }

    @PostMapping("/s/panel/update")
    public boolean update(@RequestBody TbPanel tbPanel) {
        return panelClientService.update(tbPanel);
    }

    @PostMapping("/s/panel/update/sort")
    public boolean updateSortOrder(@RequestBody List<TbPanel> list) {
        return panelClientService.updateSortOrder(list);
    }

    @PostMapping("/s/panel/del")
    public boolean del(@RequestParam("id") Integer id) {
        return panelClientService.del(id);
    }

}
