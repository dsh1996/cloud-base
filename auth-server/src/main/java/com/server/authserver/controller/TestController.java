package com.server.authserver.controller;

import com.server.common.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/test/info")
    public Result info() {
        return Result.SUCCESS("访问auth-server ：" + port);
    }
}
