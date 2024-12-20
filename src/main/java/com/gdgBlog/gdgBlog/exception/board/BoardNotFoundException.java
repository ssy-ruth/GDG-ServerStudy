package com.gdgBlog.gdgBlog.exception.board;

import com.gdgBlog.gdgBlog.exception.BaseException;

public class BoardNotFoundException extends BaseException {
    public BoardNotFoundException(String message) {
        super ("BOARD_NOT_FOUND", message);;
    }
}