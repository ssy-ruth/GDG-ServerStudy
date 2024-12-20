package com.gdgBlog.gdgBlog.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginUserRequest {
    private String email;

    private String password;
}
