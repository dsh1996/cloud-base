package com.server.authserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tab_menus")
public class Menus {
    /**
     * 主键，菜单ID
     */
     @TableId(value = "id", type = IdType.INPUT)
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

    /**
     * 父ID
     */
    @TableField(value = "parent")
    private Long parent;

    public static final String COL_NAME = "name";

    public static final String COL_ABOUT = "about";

    public static final String COL_PARENT = "parent";
}