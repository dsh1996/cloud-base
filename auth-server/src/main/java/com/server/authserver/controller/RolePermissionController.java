package com.server.authserver.controller;

import com.server.authserver.common.SuperController;
import com.server.authserver.entity.RolePermission;
import com.server.authserver.service.RolePermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rolePermission")
@Api(value = "RolePermissionController", tags = "授权管理")
public class RolePermissionController extends SuperController<RolePermission> {

    @Resource
    public void setService(RolePermissionService rolePermissionService) {
        this.service = rolePermissionService;
    }


}
