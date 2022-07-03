package com.roc.demo.upms.biz.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.roc.demo.common.core.utils.R;
import com.roc.demo.upms.api.entity.SysOauthClientDetails;
import com.roc.demo.upms.biz.service.SysOauthClientDetailsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 客户端管理模块
 * @Author penn
 * @Date 2022/7/3 10:13
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "客户端管理模块")
public class OauthClientDetailsController {

    private final SysOauthClientDetailsService sysOauthClientDetailsService;

    @GetMapping("/getClientDetailsById/{clientId}")
    public R<SysOauthClientDetails> getClientDetailsById(@PathVariable String clientId) {
        SysOauthClientDetails details = sysOauthClientDetailsService.getOne(
                Wrappers.<SysOauthClientDetails>lambdaQuery()
                        .eq(SysOauthClientDetails::getClientId, clientId), false);
        return R.ok(details);
    }
}
