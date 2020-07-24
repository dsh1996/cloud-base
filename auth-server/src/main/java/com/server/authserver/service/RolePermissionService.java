package com.server.authserver.service;

import com.server.authserver.entity.Permission;
import com.server.authserver.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RolePermissionService extends IService<RolePermission>{

    /**
     * 获取角色所具有的权限信息
     * @param roleIds 角色ID
     * @return
     */
    List<Permission> getPermissions(List<Long> roleIds);
}
