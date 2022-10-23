package com.binbini.imall.service.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/10/21/10:17
 * @Description:
 */
@Component
@FeignClient(value = "IMALL-PROVIDE-SEARCH", fallbackFactory = SearchClientFallbackFactory.class)
public interface SearchClientService {

    @GetMapping("/search/quick")
    public List<Map<String, Object>> quickSearch(@RequestParam("key") String key);
}
