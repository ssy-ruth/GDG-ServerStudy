package com.gdgBlog.gdgBlog.exception.user;

import com.gdgBlog.gdgBlog.exception.BaseException;

public class WrongPasswordException extends BaseException {
    public WrongPasswordException(final String message) {
        super("PASSWORD_NOT_MATCHED",message);
    }

    public WrongPasswordException() {
        this("비밀번호가 일치하지 않습니다.");
    }
}