package com.roc.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description 网关应用
 * @Author dongp
 * @Date 2022/7/1 0001 16:32
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DemoGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoGatewayApplication.class, args);
    }
}
