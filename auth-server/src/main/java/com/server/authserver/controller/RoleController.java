package com.server.authserver.controller;

import com.server.authserver.common.SuperController;
import com.server.authserver.entity.Role;
import com.server.authserver.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
@Api(value = "RoleController", tags = "角色管理")
public class RoleController extends SuperController<Role> {

    @Resource
    public void setService(RoleService roleService) {
        this.service = roleService;
    }
}
