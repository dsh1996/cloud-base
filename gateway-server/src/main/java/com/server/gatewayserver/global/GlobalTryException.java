package com.server.gatewayserver.global;

import com.server.common.exception.AuthException;
import com.server.common.exception.BizException;
import com.server.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
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

    @ExceptionHandler(value = AuthException.class)
    public Result validTry(AuthException e) {
        log.error(e.getMessage(), e);
        return e.getResult();
    }

    @ExceptionHandler(value = BizException.class)
    public Result validTry(BizException e) {
        log.error(e.getMessage(), e);
        return e.getResult();
    }
}
