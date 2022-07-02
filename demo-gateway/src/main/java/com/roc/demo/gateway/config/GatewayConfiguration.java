package com.roc.demo.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roc.demo.gateway.filter.PasswordDecoderFilter;
import com.roc.demo.gateway.filter.ValidateCodeGatewayFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 网关配置
 * @Author penn
 * @Date 2022/7/2 10:41
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {

    @Bean
    public ValidateCodeGatewayFilter validateCodeGatewayFilter(GatewayConfigProperties configProperties,
                                                               ObjectMapper objectMapper) {
        return new ValidateCodeGatewayFilter(configProperties, objectMapper);
    }

    @Bean
    public PasswordDecoderFilter passwordDecoderFilter(GatewayConfigProperties configProperties) {
        return new PasswordDecoderFilter(configProperties);
    }
}
