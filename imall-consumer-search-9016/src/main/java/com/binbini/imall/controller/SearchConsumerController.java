package com.binbini.imall.controller;

import com.binbini.imall.service.search.SearchClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/10/21/13:09
 * @Description:
 */
@RestController
public class SearchConsumerController {

    @Autowired
    private SearchClientService searchClientService;

    @GetMapping("/s/quick")
    public List<Map<String, Object>> quickSearch(@RequestParam("key") String key) {
        return searchClientService.quickSearch(key);
    }


}
