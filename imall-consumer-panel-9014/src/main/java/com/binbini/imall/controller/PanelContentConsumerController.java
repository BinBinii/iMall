package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.service.panel.PanelContentClientService;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/15:55
 * @Description:
 */
@RestController
public class PanelContentConsumerController {

    @Autowired
    private PanelContentClientService panelContentClientService;

    @PostMapping("/s/panel/content/create")
    public int create(@RequestBody TbPanelContent tbPanelContent) {
        return panelContentClientService.createPanelContent(tbPanelContent);
    }

    @GetMapping("/s/panel/content/get/page")
    public DataTablesResult findPanelContentSearchPage(@RequestParam("start") int start,
                                                       @RequestParam("length") int length,
                                                       @RequestParam("search") String search,
                                                       @RequestParam("minDate") String minDate,
                                                       @RequestParam("maxDate") String maxDate) {
        return panelContentClientService.findPanelContentSearchPage(start, length, search, minDate, maxDate);
    }

    @GetMapping("/s/panel/content/get")
    public TbPanelContent findPanelContentById(@RequestParam("id") Integer id) {
        return panelContentClientService.findPanelContentById(id);
    }

    @PostMapping("/s/panel/content/update")
    public boolean update(@RequestBody TbPanelContent tbPanelContent) {
        return panelContentClientService.update(tbPanelContent);
    }

    @PostMapping("/s/panel/content/update/sort")
    public boolean updateSortOrder(List<TbPanelContent> list) {
        return panelContentClientService.updateSortOrder(list);
    }

    @PostMapping("/s/panel/content/del")
    public boolean del(@RequestParam("id") Integer id) {
        return panelContentClientService.del(id);
    }

}
