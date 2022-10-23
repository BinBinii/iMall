package com.binbini.imall.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: BinBin
 * @Date: 2022/09/05/16:31
 * @Description:
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced   // 配置负载均衡实现RestTemplate
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
