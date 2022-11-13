package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.dto.EsInfo;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.service.SearchItemService;
import com.google.gson.Gson;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/22:04
 * @Description:
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    private final static Logger log= LoggerFactory.getLogger(SearchItemServiceImpl.class);

    @Autowired
    private TbItemMapper tbItemMapper;

    @Value("${item.index}")
    private String ITEM_INDEX;

    @Value("${item.type}")
    private String ITEM_TYPE;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public boolean importAllItems() throws IOException {

        // 构建批量插入请求
        BulkRequest request = new BulkRequest();
        // 设置超时时间
        request.timeout("10s");
        // 插入数据
        QueryWrapper<TbItem> queryWrapper = new QueryWrapper<>();
        List<TbItem> list = tbItemMapper.selectList(queryWrapper);
        for (TbItem tbItem:list) {
            request.add(
                    new IndexRequest(ITEM_INDEX)
                            .source(new Gson().toJson(tbItem), XContentType.JSON)
            );
        }
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        return response.hasFailures();
    }

    @Override
    public boolean deleteAllItems() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(ITEM_INDEX);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    @Override
    public EsInfo getEsInfo() {
        return null;
    }

    private void deleteDocument() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(ITEM_INDEX);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());// 是否删除成功
        client.close();
    }
}
