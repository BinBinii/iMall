package com.binbini.imall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/21:56
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@MapperScan("com.binbini.imall.mapper")
public class SearchProvideApplication_9006 {
    public static void main(String[] args) {
        SpringApplication.run(SearchProvideApplication_9006.class, args);
    }
}
