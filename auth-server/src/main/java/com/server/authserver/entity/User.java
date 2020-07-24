package com.server.authserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.server.authserver.common.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDateTime;

@Data
@TableName(value = "tab_user")
public class User extends SuperEntity {
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空", groups = {Delete.class, Update.class})
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "name")
    @NotNull(message = "请填写用户名！")
    private String name;

    /**
     * 密码
     */
    @TableField(value = "password")
    @NotNull(message = "请填写密码！", groups = {Create.class, Login.class})
    private String password;

    /**
     * 登录账号(手机号)
     */
    @TableField(value = "phone")
    @NotNull(message = "请输入有效手机号！", groups = {Create.class, Login.class})
    private String phone;

    /**
     * 头像
     */
    @TableField(value = "photo")
    private String photo;

    @TableField(value = "email")
    @Email(message = "请输入有效邮箱")
    private String email;

    @TableField(value = "logic_status")
    private Integer logicStatus;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;


    public interface Login extends Default {
    }


    public User(@NotNull(message = "请填写用户名！") String name, @NotNull(message = "请填写密码！", groups = {Create.class, Login.class}) String password, @NotNull(message = "请输入有效手机号！", groups = {Create.class, Login.class}) String phone, String photo, @Email(message = "请输入有效邮箱") String email) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.photo = photo;
        this.email = email;
    }
}