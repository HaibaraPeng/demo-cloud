package com.roc.demo.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.roc.demo.common.core.constant.CommonConstants;
import com.roc.demo.common.core.constant.SecurityConstants;
import com.roc.demo.common.core.utils.R;
import com.roc.demo.common.core.utils.ResultUtils;
import com.roc.demo.upms.api.dto.UserInfo;
import com.roc.demo.upms.api.entity.SysUser;
import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description DemoUserDetailsService
 * @Author dongp
 * @Date 2022/7/4 0004 17:55
 */
public interface DemoUserDetailsService extends UserDetailsService, Ordered {

    /**
     * 是否支持此客户端校验
     *
     * @param clientId  目标客户端
     * @param grantType
     * @return true/false
     */
    default boolean support(String clientId, String grantType) {
        return true;
    }

    /**
     * 排序值 默认取最大的
     *
     * @return 排序值
     */
    @Override
    default int getOrder() {
        return 0;
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return UserDetails
     */
    default UserDetails getUserDetails(R<UserInfo> result) {
        UserInfo info = ResultUtils.of(result).getData().orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

        Set<String> dbAuthsSet = new HashSet<>();

        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
        }

        Collection<GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        // 构造security用户
        return new DemoUser(user.getUserId(), user.getDeptId(), user.getUsername(),
                SecurityConstants.BCRYPT + user.getPassword(), user.getPhone(), true, true, true,
                StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL), authorities);
    }

    /**
     * 通过用户实体查询
     *
     * @param user user
     * @return
     */
    default UserDetails loadUserByUser(DemoUser user) {
        return this.loadUserByUsername(user.getUsername());
    }
}
