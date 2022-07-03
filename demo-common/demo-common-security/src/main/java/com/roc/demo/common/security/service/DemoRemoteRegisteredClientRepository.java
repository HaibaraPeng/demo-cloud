package com.roc.demo.common.security.service;

import com.roc.demo.common.core.constant.CacheConstants;
import com.roc.demo.common.core.constant.SecurityConstants;
import com.roc.demo.common.core.utils.ResultUtils;
import com.roc.demo.common.security.exception.OAuthClientException;
import com.roc.demo.upms.api.entity.SysOauthClientDetails;
import com.roc.demo.upms.api.feign.RemoteClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * @Description 查询客户端相关信息实现
 * @Author penn
 * @Date 2022/7/2 16:09
 */
@RequiredArgsConstructor
public class DemoRemoteRegisteredClientRepository implements RegisteredClientRepository {

    private final RemoteClientDetailsService clientDetailsService;

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RegisteredClient findById(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * 重写原生方法支持redis缓存
     *
     * @param clientId
     * @return
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public RegisteredClient findByClientId(String clientId) {
        SysOauthClientDetails clientDetails = ResultUtils
                .of(clientDetailsService.getClientDetailsById(clientId, SecurityConstants.FROM_IN))
                .assertDataNotNull(result -> new OAuthClientException("clientId 不合法"))
                .getData()
                .get();
        // TODO
        return null;
    }
}
