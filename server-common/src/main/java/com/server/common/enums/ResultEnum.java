package com.server.common.enums;

import lombok.Getter;

/**
 * 响应枚举值
 */
@Getter
public enum ResultEnum {
    OK(200, "成功"),
    SUCCESS(201, "操作成功"),
    ERROR(500, "服务异常"),
    FAILED(501, "操作失败"),
    UNAUTH(403, "没有权限");

    private int code;

    private String msg;

     ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum codeOf(int code) {
        for (ResultEnum rs : ResultEnum.values()) {
            if (rs.code == code) {
                return rs;
            }
        }
        return null;
    }
}
