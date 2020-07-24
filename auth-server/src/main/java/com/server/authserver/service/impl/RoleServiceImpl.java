package com.server.authserver.service.impl;

import com.server.authserver.entity.Role;
import com.server.authserver.service.RoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.authserver.dao.RoleMapper;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
