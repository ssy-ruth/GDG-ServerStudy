package com.gdgBlog.gdgBlog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreatePostRequest {

    private Long userId;

    private Long boardId;

    private String title;

    //private LocalDate createdAt;

    private String content;

    public PostDto toPostDto() {
        return PostDto.builder()
                //.createdAt(this.createdAt)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
