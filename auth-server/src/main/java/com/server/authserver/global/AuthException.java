package com.server.authserver.global;

import com.server.common.vo.Result;

public class AuthException extends Exception {

    private Result<Object> result;

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Exception cause) {
        super(message, cause);
        this.result = Result.UNAUTH();
    }

    public AuthException(Result<Object> result, Exception e) {
        super(result.getMsg(), e);
        this.result = result;
    }


}
