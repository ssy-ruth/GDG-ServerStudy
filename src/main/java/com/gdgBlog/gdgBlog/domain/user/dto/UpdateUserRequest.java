package com.gdgBlog.gdgBlog.domain.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    //아디 검사용, 유저 찾기용 => 토큰대체
    private String email;

    //비번 변경용
    private String password;

    //닉넴 변경용
    private String name;
}
