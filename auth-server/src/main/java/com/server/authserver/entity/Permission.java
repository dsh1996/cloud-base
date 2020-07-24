package com.server.authserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.server.authserver.common.SuperEntity;
import lombok.Data;

@Data
@TableName(value = "tab_permission")
public class Permission extends SuperEntity {
    /**
     * 权限ID
     */
     @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 权限名称
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 权限描述
     */
    @TableField(value = "description")
    private String description;

    public static final String COL_PERMISSION = "permission";

    public static final String COL_DESCRIPTION = "description";
}