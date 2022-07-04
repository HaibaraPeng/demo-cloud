package com.roc.demo.auth.support.core;

import com.roc.demo.auth.support.handler.FormAuthenticationFailureHandler;
import com.roc.demo.auth.support.handler.SsoLogoutSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * @Description 基于授权码模式 统一认证登录 spring security & sas 都可以使用 所以抽取成 HttpConfigurer
 * @Author dongp
 * @Date 2022/7/4 0004 16:25
 */
public class FormIdentityLoginConfigurer extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin -> {
                    formLogin.loginPage("/token/login");
                    formLogin.loginProcessingUrl("/token/form");
                    formLogin.failureHandler(new FormAuthenticationFailureHandler());
                })
                // SSO登出成功处理
                .logout()
                .logoutSuccessHandler(new SsoLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and().csrf().disable();
    }
}
