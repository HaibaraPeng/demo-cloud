package com.roc.demo.common.core.constant;

/**
 * @Description SecurityConstants
 * @Author penn
 * @Date 2022/7/2 10:10
 */
public interface SecurityConstants {

    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth2/token";

    /**
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * BASIC_
     */
    String BASIC = "Basic ";

    /**
     * 标志
     */
    String FROM = "from";

    /**
     * 内部
     */
    String FROM_IN = "Y";

    /**
     * 项目的license
     */
    String PROJECT_LICENSE = "https://demo4cloud.com";

    /**
     * 授权码模式confirm
     */
    String CUSTOM_CONSENT_PAGE_URI = "/token/confirm_access";

    /**
     * REDIRECT_URL
     */
    String REDIRECT_URL = "redirect_url";
}
