package com.server.common.exception;

import com.server.common.vo.Result;

public class BizException extends RuntimeException {

    private Result result;


    public Result getResult() {
        return result;
    }
}
