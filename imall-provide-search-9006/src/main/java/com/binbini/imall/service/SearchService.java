package com.binbini.imall.service;

import com.binbini.imall.dto.SearchResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/21:58
 * @Description:
 */
@Service
public interface SearchService {
    /**
     * ES商品搜索
     * @param keyword   查询关键字
     * @param page      第几页
     * @param size      一页的商品数量
     * @param sort      排序
     * @param priceGt
     * @param priceLte
     * @return
     */
    SearchResult search(String keyword, int page, int size, String sort, int priceGt, int priceLte) throws IOException;

    /**
     * restful搜索接口
     * @param key
     * @return
     */
    List<Map<String, Object>> quickSearch(String key) throws IOException;
}
