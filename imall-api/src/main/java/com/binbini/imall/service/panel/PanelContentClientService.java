package com.binbini.imall.service.panel;

import com.binbini.imall.pojo.TbPanelContent;
import com.binbini.imall.vo.DataTablesResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/29/15:56
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDER-PANEL", fallbackFactory = PanelContentClientFallbackFactory.class)
public interface PanelContentClientService {

    @PostMapping("/panel/content/create")
    public int createPanelContent(@RequestBody TbPanelContent tbPanelContent);

    @GetMapping("/panel/content/get/page")
    public DataTablesResult findPanelContentSearchPage(@RequestParam("start") int start,
                                                       @RequestParam("length") int length,
                                                       @RequestParam("search") String search,
                                                       @RequestParam("minDate") String minDate,
                                                       @RequestParam("maxDate") String maxDate);

    @GetMapping("/panel/content/get")
    public TbPanelContent findPanelContentById(@RequestParam("id") Integer id);

    @PostMapping("/panel/content/update")
    public boolean update(@RequestBody TbPanelContent tbPanelContent);

    @PostMapping("/panel/content/update/sort")
    public boolean updateSortOrder(@RequestBody List<TbPanelContent> list);

    @PostMapping("/panel/content/del")
    public boolean del(@RequestParam("id") Integer id);

}
