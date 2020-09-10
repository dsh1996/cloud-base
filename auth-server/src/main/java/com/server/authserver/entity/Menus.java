package com.server.authserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.server.authserver.common.SuperEntity;
import lombok.Data;

@Data
@TableName(value = "tab_menus")
public class Menus extends SuperEntity {
    /**
     * 主键，菜单ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述信息
     */
    @TableField(value = "about")
    private String about;

    @TableField(value = "path")
    private String path;

    @TableField(value = "component")
    private String component;

    @TableField(value = "title")
    private String title;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "redirect")
    private String redirect;

    /**
     * 父ID
     */
    @TableField(value = "parent")
    private Long parent;

    /**
     * 依赖关系 例如：[父ID.子ID]
     */
    @TableField(value = "depend")
    private String depend;


}