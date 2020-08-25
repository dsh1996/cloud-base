package com.server.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
public final class Token {
    //创建时间
    private LocalDateTime createTime = LocalDateTime.now();
    //token
    private String accessToken;
    //有效时长
    private Long time = 60L;
}
