package com.roc.demo.upms.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roc.demo.upms.api.entity.SysOauthClientDetails;
import com.roc.demo.upms.biz.mapper.SysOauthClientDetailsMapper;
import com.roc.demo.upms.biz.service.SysOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Description SysOauthClientDetailsServiceImpl
 * @Author penn
 * @Date 2022/7/3 10:30
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails>
        implements SysOauthClientDetailsService {
}
