package com.gdgBlog.gdgBlog.post.dto;

import com.gdgBlog.gdgBlog.board.Board;
import com.gdgBlog.gdgBlog.post.Post;
import com.gdgBlog.gdgBlog.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostDto {
    private Long id;

    private String title;

    private LocalDate createdAt;

    private String content;

    @Builder
    public PostDto(String title, LocalDate createdAt, String content){
        this.title=title;
        this.createdAt=createdAt;
        this.content=content;
    }
    public Post toEntity(User user, Board board){
        return Post.builder()
                .user(user)
                .board(board)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
