package com.roc.demo.common.security.service;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.util.Assert;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description DemoRedisOAuth2AuthorizationService
 * @Author dongp
 * @Date 2022/7/4 0004 16:43
 */
@RequiredArgsConstructor
public class DemoRedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final static Long TIMEOUT = 10L;
    private static final String AUTHORIZATION = "token";


    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        if (isState(authorization)) {
            String token = authorization.getAttribute("state");
            redisTemplate.opsForValue().set(
                    buildKey(OAuth2ParameterNames.STATE, token), authorization, TIMEOUT, TimeUnit.MINUTES);
        }

        if (isCode(authorization)) {
            OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
                    authorization.getToken(OAuth2AuthorizationCode.class);
            OAuth2AuthorizationCode token = authorizationCode.getToken();
            long between = ChronoUnit.MINUTES.between(token.getIssuedAt(), token.getExpiresAt());
            redisTemplate.opsForValue().set(buildKey(OAuth2ParameterNames.CODE, token.getTokenValue()), authorization,
                    between, TimeUnit.MINUTES);
        }

        if (isRefreshToken(authorization)) {
            OAuth2RefreshToken token = authorization.getRefreshToken().getToken();
            long between = ChronoUnit.MINUTES.between(token.getIssuedAt(), token.getExpiresAt());
            redisTemplate.opsForValue().set(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, token.getTokenValue()),
                    authorization, between, TimeUnit.SECONDS);
        }

        if (isAccessToken(authorization)) {
            OAuth2AccessToken token = authorization.getAccessToken().getToken();
            long between = ChronoUnit.MINUTES.between(token.getIssuedAt(), token.getExpiresAt());
            redisTemplate.opsForValue().set(buildKey(OAuth2ParameterNames.ACCESS_TOKEN, token.getTokenValue()),
                    authorization, between, TimeUnit.SECONDS);
        }
    }

    private boolean isState(OAuth2Authorization authorization) {
        return ObjectUtil.isNotNull(authorization.getAttribute("state"));
    }

    private boolean isCode(OAuth2Authorization authorization) {
        return ObjectUtil.isNotNull(authorization.getToken(OAuth2AuthorizationCode.class));
    }

    private boolean isRefreshToken(OAuth2Authorization authorization) {
        return ObjectUtil.isNotNull(authorization.getRefreshToken());
    }

    private boolean isAccessToken(OAuth2Authorization authorization) {
        return ObjectUtil.isNotNull(authorization.getAccessToken());
    }

    private String buildKey(String type, String id) {
        return String.format("%s::%s::%s", AUTHORIZATION, type, id);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");

        List<String> keys = new ArrayList<>();
        if (isState(authorization)) {
            String token = authorization.getAttribute("state");
            keys.add(buildKey(OAuth2ParameterNames.STATE, token));
        }

        if (isCode(authorization)) {
            OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
                    .getToken(OAuth2AuthorizationCode.class);
            keys.add(buildKey(OAuth2ParameterNames.CODE, authorizationCode.getToken().getTokenValue()));
        }

        if (isRefreshToken(authorization)) {
            OAuth2RefreshToken token = authorization.getRefreshToken().getToken();
            keys.add(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, token.getTokenValue()));
        }

        if (isAccessToken(authorization)) {
            OAuth2AccessToken token = authorization.getAccessToken().getToken();
            keys.add(buildKey(OAuth2ParameterNames.ACCESS_TOKEN, token.getTokenValue()));
        }

        redisTemplate.delete(keys);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        Assert.hasText(id, "id cannot be empty");
        throw new UnsupportedOperationException();
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.hasText(token, "token cannot be empty");
        Assert.notNull(tokenType, "tokenType cannot be empty");
        return (OAuth2Authorization) redisTemplate.opsForValue().get(buildKey(tokenType.getValue(), token));
    }
}
