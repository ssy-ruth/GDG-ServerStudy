package com.gdgBlog.gdgBlog.board;

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


    public static Board of(String name, Integer auth) {
        Board board = new Board();
        board.name=name;
        board.authority=auth;
        return board;
    }


    @Builder
    private Board(String name, Integer auth) {
        this.name = name;
        this.authority=auth;
    }
}
