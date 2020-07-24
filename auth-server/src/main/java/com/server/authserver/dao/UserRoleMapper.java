package com.server.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.authserver.entity.Role;
import com.server.authserver.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 获取用户所有角色信息
     * @param id 用户ID
     * @return
     */
    List<Role> getRoles(Long userId);
}