package com.server.authserver.controller;

import com.server.authserver.common.SuperController;
import com.server.authserver.entity.Permission;
import com.server.authserver.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission")
@Api(value = "PermissionController", tags = "权限管理")
public class PermissionController extends SuperController<Permission> {

    @Resource
    public void setService(PermissionService permissionService) {
        this.service = permissionService;
    }

}
