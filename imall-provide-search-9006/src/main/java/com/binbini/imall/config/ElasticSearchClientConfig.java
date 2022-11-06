package com.binbini.imall.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

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
    private Integer ES_NODE_CLIENT_PORT;

    // 注册 rest高级客户端
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ES_CONNECT_IP, ES_NODE_CLIENT_PORT,"http")
                )
        );
        return client;
    }

//    @Bean
//    public RestHighLevelClient restClient() {
//        RestHighLevelClient restClient = null;
//        try {
//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY,
//                    new UsernamePasswordCredentials(ES_USERNAME, ES_PASSWORD));
//
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//                // 信任所有
//                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    return true;
//                }
//            }).build();
//            SSLIOSessionStrategy sessionStrategy = new SSLIOSessionStrategy(sslContext, NoopHostnameVerifier.INSTANCE);
//            restClient = new RestHighLevelClient(
//                    RestClient.builder(
//                                    new HttpHost(ES_CONNECT_IP, ES_NODE_CLIENT_PORT))
//                            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                                    httpClientBuilder.disableAuthCaching();
//                                    httpClientBuilder.setSSLStrategy(sessionStrategy);
//                                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                                    return httpClientBuilder;
//                                }
//                            }));
//        } catch (Exception e) {
//            System.out.println(e);
////            LOGGER.error("elasticsearch TransportClient create error!!", e);
//        }
//        return restClient;
//    }


}
