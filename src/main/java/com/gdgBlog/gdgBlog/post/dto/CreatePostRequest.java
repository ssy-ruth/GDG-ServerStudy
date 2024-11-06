package com.gdgBlog.gdgBlog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreatePostRequest {

    private Long id;

    private Long userId;

    private Long boardId;

    private String title;

    private LocalDate date;

    private String Content;
}
