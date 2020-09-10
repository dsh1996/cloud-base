package com.server.common.vo;

import com.server.common.enums.ResultEnum;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum rs, T data) {
        this.code = rs.getCode();
        this.msg = rs.getMsg();
        this.data = data;
    }


    public static Result SUCCESS() {
        return Result.builder().code(200).msg("成功").build();
    }

    public static Result SUCCESS(String msg) {
        return Result.builder().code(200).msg(msg).build();
    }

    public static Result SUCCESS(Object data) {
        return Result.builder().code(200).msg("操作成功").data(data).build();
    }

    public static Result SUCCESS(String msg, Object data) {
        return Result.builder().code(200).msg(msg).data(data).build();
    }

    public static Result FAILED() {
        return Result.builder().code(500).msg("失败").build();
    }

    public static Result ERROR(String msg) {
        return new Result(ResultEnum.ERROR, msg);
    }

    public static Result FAILED(String msg) {
        return Result.builder().code(501).msg(msg).build();
    }

    public static Result UNAUTH() {
        return Result.builder().code(403).msg("无权操作,请先授权!").build();
    }

    public static Result NO_TOKEN() {
        return Result.builder().code(401).msg("令牌丢失，服务拒绝访问!").build();
    }

    public static Result SERVER_BUSY() {
        return Result.builder().code(500).msg("服务忙，请重试.").build();
    }

}
