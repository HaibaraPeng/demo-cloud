package com.roc.demo.common.security.service;

import com.roc.demo.common.core.constant.CacheConstants;
import com.roc.demo.common.core.constant.SecurityConstants;
import com.roc.demo.common.core.utils.R;
import com.roc.demo.upms.api.dto.UserInfo;
import com.roc.demo.upms.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description 用户详细信息
 * @Author dongp
 * @Date 2022/7/4 0004 18:32
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class DemoUserDetailsServiceImpl implements DemoUserDetailsService {
    private final RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (DemoUser) cache.get(username).get();
        }

        R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        if (cache != null) {
            cache.put(username, userDetails);
        }
        return userDetails;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}
