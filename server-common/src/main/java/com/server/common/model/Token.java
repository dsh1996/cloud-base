package com.server.common.model;

import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Token {
    //创建时间
    private LocalDateTime createTime = LocalDateTime.now();
    //token
    private String accessToken;
    //有效时长
    private Long time = 60L;
}
