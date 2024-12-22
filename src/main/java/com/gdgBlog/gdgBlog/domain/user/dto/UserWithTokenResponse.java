package com.gdgBlog.gdgBlog.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserWithTokenResponse {
    private Long id;
    private String name;
    private String accessToken;
    private String refreshToken;
    private Long expire; //액세스 토큰 만료시간
}
