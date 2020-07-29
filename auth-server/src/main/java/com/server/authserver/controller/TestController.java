package com.server.authserver.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.server.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class TestController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/test/info")
    public Result info() {
        return Result.SUCCESS("访问auth-server ：" + port);
    }

    @GetMapping("/test/hy")
    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public Result hystrixTest() throws Exception {
        log.warn("waiting...  ");
        TimeUnit.SECONDS.sleep(10);
        return Result.SUCCESS();
    }


    public Result fallback() {
        log.warn("fall back... ");
        return Result.SERVER_BUSY();
    }
}
