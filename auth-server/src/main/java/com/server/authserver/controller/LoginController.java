package com.server.authserver.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.server.authserver.dto.UserDTO;
import com.server.authserver.entity.Role;
import com.server.authserver.entity.User;
import com.server.authserver.entity.UserRole;
import com.server.authserver.service.RoleService;
import com.server.authserver.service.UserRoleService;
import com.server.authserver.service.UserService;
import com.server.authserver.vo.Meta;
import com.server.authserver.vo.RouteVo;
import com.server.common.model.Token;
import com.server.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "LoginController", tags = "认证")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    public Result doLogin(@RequestBody @Validated(User.Login.class) User user) {
        String token = userService.login(user);
        Token token1 = new Token();
        token1.setAccessToken(token);
        //存储用户信息
        return StrUtil.isNotBlank(token) ? Result.SUCCESS("登录成功~", token1) : Result.FAILED("登录失败~");
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Validated User user) {
        boolean save = userService.register(user);
        return Result.SUCCESS(save);
    }

    @ApiOperation(value = "登出", notes = "登出")
    @PostMapping("/logout")
    public Result logout() {

        SecurityUtils.getSubject().logout();
        return Result.SUCCESS("登出成功");
    }

    @PostMapping("/info")
    @ApiOperation(value = "基本信息", notes = "获取当前登录人的基本信息")
    public Result info() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user = (User) principals.getPrimaryPrincipal();
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        List<String> roles = userRoleService.getRoles(user.getId()).stream().map(Role::getName).collect(Collectors.toList());
        userDTO.setPermissions(roles);
        return Result.SUCCESS(userDTO);
    }

    @GetMapping("/unauth")
    @ApiOperation(value = "无权访问", hidden = true)
    public Result unauth() {
        return Result.UNAUTH();
    }
}
