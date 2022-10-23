package com.binbini.imall.service.search;

import feign.hystrix.FallbackFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/10/21/10:17
 * @Description:
 */
public class SearchClientFallbackFactory implements FallbackFactory {
    @Override
    public SearchClientService create(Throwable throwable) {
        return new SearchClientService() {
            @Override
            public List<Map<String, Object>> quickSearch(String key) {
                return null;
            }
        };
    }
}
