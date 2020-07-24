package com.server.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.authserver.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}