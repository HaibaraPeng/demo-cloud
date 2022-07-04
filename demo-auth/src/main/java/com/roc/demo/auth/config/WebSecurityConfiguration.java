//package com.roc.demo.auth.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @Description 服务安全相关配置
// * @Author dongp
// * @Date 2022/7/4 0004 14:39
// */
//@EnableWebSecurity(debug = true)
//public class WebSecurityConfiguration {
//
//    /**
//     * spring security 默认的安全策略
//     *
//     * @param http security注入点
//     * @return SecurityFilterChain
//     * @throws Exception
//     */
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        // 开放自定义的部分端点
//        http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/token/*").permitAll()
//                // 避免iframe同源无法登录
//                .anyRequest().authenticated()).headers().frameOptions().sameOrigin();
//        // TODO 表单登录个性化
//        // 处理 UsernamePasswordAuthenticationToken
////        http.authenticationProvider(new PigDaoAuthenticationProvider());
//        return http.build();
//    }
//
//    /**
//     * 暴露静态资源
//     * <p>
//     * https://github.com/spring-projects/spring-security/issues/10938
//     *
//     * @param http
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Order(0)
//    SecurityFilterChain resources(HttpSecurity http) throws Exception {
//        http.requestMatchers((matchers) -> matchers.antMatchers("/actuator/**", "/css/**", "/error"))
//                .authorizeRequests((authorize) -> authorize.anyRequest().permitAll()).requestCache().disable()
//                .securityContext().disable().sessionManagement().disable();
//        return http.build();
//    }
//}
