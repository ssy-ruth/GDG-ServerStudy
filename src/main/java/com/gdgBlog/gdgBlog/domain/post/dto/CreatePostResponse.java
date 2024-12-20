package com.gdgBlog.gdgBlog.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreatePostResponse {

    private String title;

    private LocalDate createdAt;

    private String content;

    @Builder
    public CreatePostResponse(String title, String content, LocalDate createdAt){
        this.title=title;
        this.content=content;
        this.createdAt=createdAt;
    }

    public static CreatePostResponse toDto(PostDto post){
        return CreatePostResponse.builder()
                .createdAt(post.getCreatedAt())
                .content(post.getContent())
                .title(post.getTitle())
                .build();
    }
}
