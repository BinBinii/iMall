package com.binbini.imall.controller;

import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.service.PanelContentService;
import com.binbini.imall.vo.DataTablesResult;
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
@RequestMapping(value = "/panel/content/")
public class PanelContentController {

    @Autowired
    private PanelContentService panelContentService;

    @PostMapping("create")
    public int createPanelContent(@RequestBody TbPanelContent tbPanelContent) {
        return panelContentService.createPanelContent(tbPanelContent);
    }

    @GetMapping("get/page")
    public DataTablesResult findPanelContentSearchPage(@RequestParam("start") int start,
                                                       @RequestParam("length") int length,
                                                       @RequestParam("search") String search,
                                                       @RequestParam("minDate") String minDate,
                                                       @RequestParam("maxDate") String maxDate) {
        return panelContentService.findPanelContentSearchPage(start, length, search, minDate, maxDate);
    }

    @GetMapping("get")
    @HystrixCommand(fallbackMethod = "hystrixGetTbPanelContent")
    public TbPanelContent findPanelContentById(@RequestParam("id") Integer id) {
        return panelContentService.findPanelContentById(id);
    }

    @PostMapping("update")
    public boolean update(@RequestBody TbPanelContent tbPanelContent) {
        return panelContentService.update(tbPanelContent);
    }

    @GetMapping("update/sort")
    public boolean updateSortOrder(List<TbPanelContent> list) {
        return panelContentService.updateSortOrder(list);
    }

    @PostMapping("del")
    public boolean del(@RequestParam("id") Integer id) {
        return panelContentService.del(id);
    }

    public TbPanelContent hystrixGetTbPanelContent(@RequestParam("id") Integer id) {
        return new TbPanelContent()
                .setId(id)
                .setPic_url("id => " + id + " No corresponding information, null -- @Hystrix");
    }

}
