package com.roc.demo.upms.api.feign;

import com.roc.demo.common.core.constant.SecurityConstants;
import com.roc.demo.common.core.constant.ServiceNameConstants;
import com.roc.demo.common.core.utils.R;
import com.roc.demo.upms.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Description RemoteUserService
 * @Author dongp
 * @Date 2022/7/4 0004 18:33
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @param from     调用标志
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);
}
