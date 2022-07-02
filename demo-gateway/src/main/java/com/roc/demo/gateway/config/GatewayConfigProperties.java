package com.roc.demo.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * @Description 网关配置文件
 * @Author penn
 * @Date 2022/7/2 15:08
 */
@Data
@RefreshScope
@ConfigurationProperties("gateway")
public class GatewayConfigProperties {

    /**
     * 网关解密登录前端密码 秘钥 {@link com.roc.demo.gateway.filter.PasswordDecoderFilter}
     */
    private String encodeKey;

    /**
     * 网关不需要校验验证码的客户端 {@link com.roc.demo.gateway.filter.ValidateCodeGatewayFilter}
     */
    private List<String> ignoreClients;
}
