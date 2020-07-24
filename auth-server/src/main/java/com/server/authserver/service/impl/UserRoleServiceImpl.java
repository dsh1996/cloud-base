package com.server.authserver.service.impl;

import com.server.authserver.common.Assert;
import com.server.authserver.entity.Role;
import com.server.authserver.entity.UserRole;
import com.server.authserver.service.UserRoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.authserver.dao.UserRoleMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRoles(Long id) {
        Assert.notNull(id, "未知用户");
        return userRoleMapper.getRoles(id);
    }
}
