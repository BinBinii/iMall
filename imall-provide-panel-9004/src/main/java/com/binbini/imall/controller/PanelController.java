package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbPanel;
import com.binbini.imall.service.PanelService;
import com.binbini.imall.vo.PanelVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/11:12
 * @Description:
 */
@RestController
@RequestMapping(value = "/panel/")
public class PanelController {

    @Autowired
    private PanelService panelService;

    @PostMapping("create")
    public int createPanel(@RequestBody TbPanel tbPanel) {
        return panelService.createPanel(tbPanel);
    }

    @GetMapping("get/page")
    public List<PanelVo> findAllPanel() {
        return panelService.findAllPanel();
    }

    @GetMapping("get")
    @HystrixCommand(fallbackMethod = "hystrixGetTbPanel")
    public TbPanel findPanelById(@RequestParam("id") Integer id) {
        return panelService.findPanelById(id);
    }

    @PostMapping("update")
    public boolean update(@RequestBody TbPanel tbPanel) {
        return panelService.update(tbPanel);
    }

    @PostMapping("update/sort")
    public boolean updateSortOrder(@RequestBody List<TbPanel> list) {
        return panelService.updateSortOrder(list);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return panelService.del(id);
    }

    public TbPanel hystrixGetTbPanel(@RequestParam("id") Integer id) {
        return new TbPanel()
                .setId(id)
                .setName("id => " + id + " No corresponding information, null -- @Hystrix");
    }

}
