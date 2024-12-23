package com.gdgBlog.gdgBlog.domain.post;

import com.gdgBlog.gdgBlog.domain.board.Board;
import com.gdgBlog.gdgBlog.domain.post.dto.PostDto;
import com.gdgBlog.gdgBlog.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate createdAt;

    private String content;

    @Builder
    private Post(User user, Board board, String title, String content){
        this.user=user;
        this.board=board;
        this.title=title;
        this.content=content;
        this.createdAt=LocalDate.now();
    }

    public void updatePost(PostDto dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
        this.createdAt=LocalDate.now();
    }

    public PostDto toPostDto(){
        return PostDto.builder()
                .title(this.title)
                .content(this.content)
                .createdAt(this.createdAt)
                .build();
    }
}
