package com.roc.demo.auth.support.handler;

import com.roc.demo.common.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登录失败处理器
 * @Author dongp
 * @Date 2022/7/4 0004 16:20
 */
@Slf4j
public class DemoAuthenticationFailureEventHandler implements AuthenticationFailureHandler {

    private final MappingJackson2HttpMessageConverter errorHttpResponseConverter = new MappingJackson2HttpMessageConverter();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter(OAuth2ParameterNames.USERNAME);

        log.info("用户：{} 登录失败，异常：{}", username, exception.getLocalizedMessage());
        // TODO 日志
        // 写出错误信息
        sendErrorResponse(response, exception);
    }

    private void sendErrorResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        this.errorHttpResponseConverter.write(R.failed(exception.getLocalizedMessage()), MediaType.APPLICATION_JSON,
                httpResponse);
    }
}
