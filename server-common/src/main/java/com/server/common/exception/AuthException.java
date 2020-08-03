package com.server.common.exception;

import com.server.common.vo.Result;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthException extends Exception {

    private Result result = Result.UNAUTH();

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Result result) {
        super(message);
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
