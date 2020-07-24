package com.server.authserver.controller;

import cn.hutool.core.util.StrUtil;
import com.server.authserver.entity.User;
import com.server.authserver.service.UserService;
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

@RestController
@Api(value = "LoginController", tags = "认证")
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录", notes = "登录")
    @GetMapping("/login")
    public Result doLogin(@Validated(User.Login.class) User user) {
        String token = userService.login(user);
        //存储用户信息
        return StrUtil.isNotBlank(token) ? Result.SUCCESS("登录成功~", token) : Result.FAILED("登录失败~");
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

    @GetMapping("/info")
    @ApiOperation(value = "基本信息", notes = "获取当前登录人的基本信息")
    public Result info() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user = (User) principals.getPrimaryPrincipal();
        return Result.SUCCESS(user);
    }

    @GetMapping("/unauth")
    public Result unauth() {
        return Result.UNAUTH();
    }
}
