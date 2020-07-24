package com.server.authserver.controller;

import com.server.authserver.common.SuperController;
import com.server.authserver.entity.User;
import com.server.authserver.service.UserService;
import com.server.common.vo.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "用户维护")
public class UserController extends SuperController<User> {

    @Resource
    public void setService(UserService userService) {
        this.service = userService;
    }


    @GetMapping("/init")
    public Result init() throws InterruptedException {
        log.info("is ok ....");
       TimeUnit.SECONDS.sleep(30);
       log.info("-----------------end---------------------");
        return Result.SUCCESS("触达成功！");
    }


    @Async
    public void doing() throws InterruptedException {
        System.err.println("start");
        TimeUnit.SECONDS.sleep(5);
        System.err.println("end");
    }

}


