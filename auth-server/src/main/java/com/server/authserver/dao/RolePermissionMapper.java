package com.server.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.authserver.entity.Permission;
import com.server.authserver.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    /**
     * 获取角色权限信息
     * @param roleIds 角色
     * @return
     */
    List<Permission> getPermission(List<Long> roleIds);
}