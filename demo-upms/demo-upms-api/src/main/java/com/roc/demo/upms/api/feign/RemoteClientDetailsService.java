package com.roc.demo.upms.api.feign;

import com.roc.demo.common.core.constant.SecurityConstants;
import com.roc.demo.common.core.constant.ServiceNameConstants;
import com.roc.demo.common.core.utils.R;
import com.roc.demo.upms.api.entity.SysOauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Description RemoteClientDetailsService
 * @Author penn
 * @Date 2022/7/2 16:39
 */
@FeignClient(contextId = "remoteClientDetailsService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteClientDetailsService {

    /**
     * 通过clientId 查询客户端信息
     *
     * @param clientId 用户名
     * @param from     调用标志
     * @return R
     */
    @GetMapping("/client/getClientDetailsById/{clientId}")
    R<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId,
                                                  @RequestHeader(SecurityConstants.FROM) String from);
}
