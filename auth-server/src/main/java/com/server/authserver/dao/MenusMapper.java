package com.server.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.authserver.entity.Menus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenusMapper extends BaseMapper<Menus> {
}