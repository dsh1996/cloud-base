package com.server.gatewayserver.global;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.server.common.exception.AuthException;
import com.server.common.exception.BizException;
import com.server.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

@Slf4j
public class GlobalTryException extends DefaultErrorWebExceptionHandler {

    public GlobalTryException(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
        log.info("----------------全局异常拦截初始化中...-----------------");
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);
        log.error("gateway find global error, visit path is {}, msg is:  {} ", request.path(), error.getMessage());
        if (error instanceof AuthException) {
            return BeanUtil.beanToMap(((AuthException) error).getResult());
        }
        if (error instanceof NotFoundException) {
            return BeanUtil.beanToMap(Result.FAILED("您的访问被迷路了，请稍后重试或联系管理员."));
        }
        if (error instanceof TimeoutException) {
            return BeanUtil.beanToMap(Result.FAILED("服务堵车啦，请重新尝试."));
        }
        if(error instanceof HystrixRuntimeException){
            return BeanUtil.beanToMap(Result.FAILED("服务堵车啦，请重新尝试."));
        }
        return BeanUtil.beanToMap(Result.ERROR("服务异常，请稍后再试."));
    }


    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return MapUtil.getInt(errorAttributes, "code");
    }
}

