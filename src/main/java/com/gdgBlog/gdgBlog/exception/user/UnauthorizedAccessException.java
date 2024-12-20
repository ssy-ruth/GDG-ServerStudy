package com.gdgBlog.gdgBlog.exception.user;

import com.gdgBlog.gdgBlog.exception.BaseException;

public class UnauthorizedAccessException extends BaseException {

    public UnauthorizedAccessException(final String message) {
        super("UNAUTHORIZED_ACCESS",message);
    }

    public UnauthorizedAccessException() {
        this("잘못된 접근입니다.");
    }
}
