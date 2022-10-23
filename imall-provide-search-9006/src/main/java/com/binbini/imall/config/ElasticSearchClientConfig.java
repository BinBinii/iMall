package com.binbini.imall.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: BinBin
 * @Date: 2022/10/19/12:22
 * @Description:
 */
@Configuration
public class ElasticSearchClientConfig {

    @Value("${es.connect.ip}")
    private String ES_CONNECT_IP;

    @Value("${es.node.client.port}")
    private String ES_NODE_CLIENT_PORT;

    // 注册 rest高级客户端
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ES_CONNECT_IP,9200,"http")
                )
        );
        return client;
    }

}
