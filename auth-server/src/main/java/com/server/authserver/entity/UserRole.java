package com.server.authserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.server.authserver.common.SuperEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tab_user_role")
public class UserRole extends SuperEntity {
    /**
     * 用户ID
     */
     @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
     @TableField(value = "role_id")
    private Long roleId;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_CREATE_TIME = "create_time";
}