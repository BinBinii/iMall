package com.binbini.imall.service.impl;

import com.binbini.imall.dto.SearchResult;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.service.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.Highlighter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/22:04
 * @Description:
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public SearchResult search(String keyword, int page, int size, String sort, int priceGt, int priceLte) throws IOException {
        // 构建搜索请求
        SearchRequest request = new SearchRequest();
        // 设置搜索条件，使用该构建器进行查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);
        builder.query(matchQueryBuilder);
        // 将搜索条件放入搜索请求中
        request.source(builder);
        // 客户端执行请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHit[] hits = response.getHits().getHits();
        System.out.println("共查询到"+hits.length+"条数据");
        System.out.println("查询结果：");
        for (int i = 0; i < hits.length; i++) {
            System.out.println(hits[i].getSourceAsString());
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> quickSearch(String key) throws IOException {
        String[] keyArray = key.split(" ");
        List<Map<String, Object>> list = new ArrayList<>();
        for (String item:keyArray) {
            // 构建搜索请求
            SearchRequest request = new SearchRequest();
            // 设置搜索条件，使用该构建器进行查询
            SearchSourceBuilder builder = new SearchSourceBuilder();
            QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", item);
            builder.query(queryBuilder);
            // 将搜索条件放入搜索请求中
            request.source(builder);
            // 客户端执行请求
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            // 高亮
            for (SearchHit hit : response.getHits().getHits()) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Object id = sourceAsMap.get("id");
                String itemTitle = String.valueOf(sourceAsMap.get("title"));
                boolean flag = true;
                for (String keyItem:keyArray) {
                    itemTitle = replacementInfo(itemTitle, keyItem, "<font color='#FF6720'>", "</font>");
                    if (itemTitle.equals("-1")) {
                        flag = false;
                    }
                }
                if (flag) {
                    boolean containsFlag = true;
                    for (Map<String,Object> mapItem:list) {
                        if (mapItem.get("id").equals(id)) {
                            containsFlag = false;
                            break;
                        }
                    }
                    if (containsFlag) {
                        sourceAsMap.put("title", itemTitle);
                        list.add(hit.getSourceAsMap());
                    }

                }
            }
        }

        return list;
    }

    private static String replacementInfo(String str, String keyword, String before, String rear) {
        StringBuilder stringBuilder = new StringBuilder(str);
        //字符第一次出现的位置
        int index = stringBuilder.indexOf(keyword);
        if (index == -1) {
            return "-1";
        }
        while (index != -1) {
            stringBuilder.insert(index, before);
            stringBuilder.insert(index + before.length() + keyword.length(), rear);
            //下一次出现的位置，
            index = stringBuilder.indexOf(keyword, index + before.length() + keyword.length() + rear.length() - 1);
        }
        return stringBuilder.toString();
    }
}
