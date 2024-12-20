package com.gdgBlog.gdgBlog.domain.board;

import com.gdgBlog.gdgBlog.domain.board.dto.BoardResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer authority;


    @Builder
    private Board(String name, Integer auth) {
        this.name = name;
        this.authority=auth;
    }

    public BoardResponse toDto() {
        return BoardResponse.builder()
                .name(this.name)
                .auth(this.authority)
                .build();
    }
}
