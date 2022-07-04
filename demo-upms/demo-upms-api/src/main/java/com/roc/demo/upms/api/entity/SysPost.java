package com.roc.demo.upms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.roc.demo.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 岗位管理
 * @Author dongp
 * @Date 2022/7/4 0004 18:11
 */
@Data
@TableName("sys_post")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "岗位信息表")
public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "岗位ID")
    private Long postId;

    /**
     * 岗位编码
     */
    @Schema(description = "岗位编码")
    private String postCode;

    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    private String postName;

    /**
     * 岗位排序
     */
    @Schema(description = "岗位排序")
    private Integer postSort;

    /**
     * 是否删除 -1：已删除 0：正常
     */
    @Schema(description = "是否删除  -1：已删除  0：正常")
    private String delFlag;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String remark;
}
