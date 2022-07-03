package com.roc.demo.upms.biz;

import com.roc.demo.common.feign.annotation.EnableDemoFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description 用户统一管理系统
 * @Author penn
 * @Date 2022/7/3 10:01
 */
@EnableDemoFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DemoUpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoUpmsApplication.class, args);
    }
}
