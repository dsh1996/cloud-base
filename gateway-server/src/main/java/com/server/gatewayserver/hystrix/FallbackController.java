package com.server.gatewayserver.hystrix;

import com.server.common.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Result fallback() {
        return Result.SERVER_BUSY();
    }
}
