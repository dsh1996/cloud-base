package com.server.authserver.controller;

import com.server.authserver.common.SuperController;
import com.server.authserver.entity.UserRole;
import com.server.authserver.service.UserRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userRole")
@Api(value = "UserRoleController", tags = "角色分配")
public class UserRoleController extends SuperController<UserRole> {

    @Resource
    public void setService(UserRoleService userRoleService) {
        this.service = userRoleService;
    }


}
