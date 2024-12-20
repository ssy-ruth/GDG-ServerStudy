package com.gdgBlog.gdgBlog.domain.board.dto;

import lombok.Builder;

public class BoardResponse {
    private String name;
    private Integer authority;

    @Builder
    private BoardResponse(String name, Integer auth) {
        this.name = name;
        this.authority=auth;
    }
}
