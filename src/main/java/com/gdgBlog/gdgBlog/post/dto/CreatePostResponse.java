package com.gdgBlog.gdgBlog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreatePostResponse {

    private String title;

    private LocalDate date;

    private String content;

    public static CreatePostResponse fromPost(PostDto post){
        return new CreatePostResponse(
                post.getTitle(),
                post.getDate(),
                post.getContent()
        );
    }
}
