package com.gdgBlog.gdgBlog.domain.board.dto;

import com.gdgBlog.gdgBlog.domain.board.Board;
import lombok.Getter;

@Getter
public class CreateBoardRequest {
    private Long userId;
    private String name;
    private Integer authority;

    public Board toEntity() {
        return Board.builder()
                .name(name)
                .auth(authority)
                .build();
    }
}
