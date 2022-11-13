package com.binbini.imall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/08:31
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@MapperScan("com.binbini.imall.mapper")
public class CommentProvideApplication_9005 {
    public static void main(String[] args) {
        SpringApplication.run(CommentProvideApplication_9005.class, args);
    }
}
