package com.gdgBlog.gdgBlog.domain.board.controller;

import com.gdgBlog.gdgBlog.domain.board.dto.BoardRequest;
import com.gdgBlog.gdgBlog.domain.board.dto.BoardResponse;
import com.gdgBlog.gdgBlog.domain.board.dto.CreateBoardRequest;
import com.gdgBlog.gdgBlog.domain.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponse> create(@RequestBody CreateBoardRequest request) {
        BoardResponse response = boardService.createBoard(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody BoardRequest request) {
        boardService.delete(request);
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }
}
