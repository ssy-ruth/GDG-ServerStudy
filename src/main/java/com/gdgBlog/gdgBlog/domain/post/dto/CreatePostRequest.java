package com.gdgBlog.gdgBlog.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostRequest {

    private Long userId;

    private Long boardId;

    private String title;

    private String content;

    public PostDto toPostDto() {
        return PostDto.builder()
                //.createdAt(this.createdAt)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
