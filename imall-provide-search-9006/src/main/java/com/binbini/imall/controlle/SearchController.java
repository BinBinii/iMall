package com.binbini.imall.controlle;

import com.binbini.imall.dto.SearchResult;
import com.binbini.imall.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/10/19/16:42
 * @Description:
 */
@RestController
@RequestMapping(value = "/search/")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("page")
    public SearchResult search(@RequestParam("keyword") String keyword,
                               @RequestParam("page") int page,
                               @RequestParam("size") int size,
                               @RequestParam("sort") String sort,
                               @RequestParam("priceGt") int priceGt,
                               @RequestParam("priceLte") int priceLte) throws IOException {
        return searchService.search(keyword, page, size, sort, priceGt, priceLte);
    }

    @GetMapping("quick")
    public List<Map<String, Object>> quickSearch(@RequestParam("key") String key) throws IOException {
        return searchService.quickSearch(key);
    }
}
