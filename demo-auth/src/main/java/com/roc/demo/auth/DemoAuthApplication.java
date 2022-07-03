package com.roc.demo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description 认证授权中心
 * @Author dongp
 * @Date 2022/7/1 0001 17:57
 */
@EnableFeignClients
@SpringBootApplication
public class DemoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAuthApplication.class, args);
    }
}
