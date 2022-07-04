package com.roc.demo.auth.support.handler;

import cn.hutool.core.util.StrUtil;
import com.roc.demo.common.core.constant.SecurityConstants;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description sso 退出功能 ，根据客户端传入跳转
 * @Author dongp
 * @Date 2022/7/4 0004 16:34
 */
public class SsoLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (response == null) {
            return;
        }

        // 获取请求参数中是否包含 回调地址
        String redirectUrl = request.getParameter(SecurityConstants.REDIRECT_URL);
        if (StrUtil.isNotBlank(redirectUrl)) {
            response.sendRedirect(redirectUrl);
        } else if (StrUtil.isNotBlank(request.getHeader(HttpHeaders.REFERER))) {
            // 默认跳转referer地址
            String referer = request.getHeader(HttpHeaders.REFERER);
            response.sendRedirect(referer);
        }
    }
}
