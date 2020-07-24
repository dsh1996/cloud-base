package com.server.authserver.service.impl;

import com.server.authserver.entity.Permission;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.authserver.dao.PermissionMapper;
import com.server.authserver.service.PermissionService;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

}
