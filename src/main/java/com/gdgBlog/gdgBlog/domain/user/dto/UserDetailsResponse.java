package com.gdgBlog.gdgBlog.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailsResponse {
    private Long id;
    private String email;
    private String name;
}
