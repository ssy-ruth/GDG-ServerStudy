package com.gdgBlog.gdgBlog.post;

import com.gdgBlog.gdgBlog.board.Board;
import com.gdgBlog.gdgBlog.post.dto.PostDto;
import com.gdgBlog.gdgBlog.user.User;
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
    private LocalDate date;

    private String content;

    //dto할때
    public static Post of(PostDto dto, User user, Board board) {
        Post post = new Post();
        post.title=dto.getTitle();
        post.date=dto.getDate();
        post.content=dto.getContent();
        post.user=user;
        post.board=board;
        return post;
    }


//    @Builder
//    private Post(User user, Board board, String title, String content){
//        this.user=user;
//        this.board=board;
//        this.title=title;
//        this.content=content;
//    }

    public void updatePost(PostDto dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
        this.date=dto.getDate();
    }
}
