package com.roc.demo.upms.api.dto;

import com.roc.demo.upms.api.entity.SysPost;
import com.roc.demo.upms.api.entity.SysRole;
import com.roc.demo.upms.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description UserInfo
 * @Author dongp
 * @Date 2022/7/4 0004 18:02
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户基本信息
     */
    private SysUser sysUser;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Long[] roles;

    /**
     * 角色集合
     */
    private List<SysRole> roleList;

    /**
     * 岗位集合
     */
    private Long[] posts;

    /**
     * 岗位集合
     */
    private List<SysPost> postList;
}
