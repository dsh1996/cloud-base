package com.server.authserver.global;

import com.server.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalTryException {

    @ExceptionHandler(value = BindException.class)
    public Result validTry(BindException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(400).msg(e.getFieldError().getDefaultMessage()).build();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result validTry(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(400).msg(e.getMessage()).build();
    }

    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public Result validTry(IncorrectCredentialsException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(403).msg("密码错误!").build();
    }

    @ExceptionHandler(value = UnknownAccountException.class)
    public Result validTry(UnknownAccountException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(403).msg("账号不存在!").build();
    }


    @ExceptionHandler(value = UnauthorizedException.class)
    public Result validTry(UnauthorizedException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(403).msg("您还没有该项操作权限，请先授权!").build();
    }


    @ExceptionHandler(value = AuthenticationException.class)
    public Result validTry(AuthenticationException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(403).msg("您还未注册，请先注册！").build();
    }

    @ExceptionHandler(value = ShiroException.class)
    public Result validTry(ShiroException e) {
        log.error(e.getMessage(), e);
        return Result.builder().code(403).msg("操作已越权，将无法进行该项操作!").build();
    }
}
