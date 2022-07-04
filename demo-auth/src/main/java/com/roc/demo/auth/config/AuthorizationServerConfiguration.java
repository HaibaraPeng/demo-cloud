package com.roc.demo.auth.config;

import com.roc.demo.auth.support.core.DemoDaoAuthenticationProvider;
import com.roc.demo.auth.support.core.FormIdentityLoginConfigurer;
import com.roc.demo.auth.support.handler.DemoAuthenticationFailureEventHandler;
import com.roc.demo.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

/**
 * @Description 认证服务器配置
 * @Author dongp
 * @Date 2022/7/4 0004 14:12
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

    private final OAuth2AuthorizationService authorizationService;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();


        http.apply(authorizationServerConfigurer
                // 个性化认证授权端点
                .tokenEndpoint((tokenEndpoint) -> {
                    // 注入自定义的授权认证Converter
                    tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter())
                            // 登录失败处理器
                            .errorResponseHandler(new DemoAuthenticationFailureEventHandler());
                })
                // 个性化客户端认证
                .clientAuthentication(oAuth2ClientAuthenticationConfigurer ->
                        oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new DemoAuthenticationFailureEventHandler()))
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)));


        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        DefaultSecurityFilterChain securityFilterChain = http
                .requestMatcher(endpointsMatcher)
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                // redis存储token的实现
                .apply(authorizationServerConfigurer.authorizationService(authorizationService))
                .providerSettings(ProviderSettings.builder().issuer(SecurityConstants.PROJECT_LICENSE).build())
                // 授权码登录的登录页个性化
                .and().apply(new FormIdentityLoginConfigurer()).and().build();

        // 注入自定义授权模式实现
        addCustomOAuth2GrantAuthenticationProvider(http);
        return securityFilterChain;
    }

    /**
     * request -> xToken 注入请求转换器
     *
     * @return DelegatingAuthenticationConverter
     */
    private AuthenticationConverter accessTokenRequestConverter() {
        return new DelegatingAuthenticationConverter(Arrays.asList(
//                new OAuth2ResourceOwnerPasswordAuthenticationConverter(),
//                new OAuth2ResourceOwnerSmsAuthenticationConverter(),
                new OAuth2RefreshTokenAuthenticationConverter(),
                new OAuth2ClientCredentialsAuthenticationConverter(),
                new OAuth2AuthorizationCodeAuthenticationConverter(),
                new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
    }

    /**
     * 注入授权模式实现提供方
     * <p>
     * 1. 密码模式 </br>
     * 2. 短信登录 </br>
     */
    @SuppressWarnings("unchecked")
    private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
//
//        OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider =
//                new OAuth2ResourceOwnerPasswordAuthenticationProvider(authenticationManager, authorizationService,
//                        oAuth2TokenGenerator());

//        OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
//                authenticationManager, authorizationService, oAuth2TokenGenerator());

        // 处理 UsernamePasswordAuthenticationToken
        http.authenticationProvider(new DemoDaoAuthenticationProvider());
//		// 处理 OAuth2ResourceOwnerPasswordAuthenticationToken
//		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
//		// 处理 OAuth2ResourceOwnerSmsAuthenticationToken
//		http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
    }
}
