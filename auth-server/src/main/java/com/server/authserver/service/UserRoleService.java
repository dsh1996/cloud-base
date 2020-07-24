package com.server.authserver.service;

import com.server.authserver.entity.Role;
import com.server.authserver.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserRoleService extends IService<UserRole>{


    /**
     * 获取用户角色
     * @param id
     * @return
     */
    List<Role> getRoles(Long id);
}
