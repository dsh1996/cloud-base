package com.server.authserver.service.impl;

import com.server.authserver.common.Assert;
import com.server.authserver.entity.Permission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.authserver.dao.RolePermissionMapper;
import com.server.authserver.entity.RolePermission;
import com.server.authserver.service.RolePermissionService;
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService{

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> getPermissions(List<Long> roleIds) {
        return rolePermissionMapper.getPermission(roleIds);
    }
}
