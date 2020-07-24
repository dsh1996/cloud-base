package com.server.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.authserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}