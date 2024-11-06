package com.gdgBlog.gdgBlog.post.dto;

import com.gdgBlog.gdgBlog.post.Post;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    private Long id;

    private String title;

    private LocalDate date;

    private String content;

    public PostDto(String title, LocalDate date, String content){
        this.title=title;
        this.date=date;
        this.content=content;
    }
    public static PostDto from(Post entity){
        return new PostDto(
                entity.getTitle(),
                entity.getDate(),
                entity.getContent()
        );
    }
    public static PostDto convertToPostDto(CreatePostRequest request) {
            return new PostDto(
                    request.getTitle(),
                    request.getDate(),
                    request.getContent()
        );
    }
}
