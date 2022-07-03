package com.roc.demo.auth;

import com.roc.demo.common.feign.annotation.EnableDemoFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 认证授权中心
 * @Author dongp
 * @Date 2022/7/1 0001 17:57
 */
@EnableDemoFeignClients
@SpringBootApplication
public class DemoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAuthApplication.class, args);
    }
}
