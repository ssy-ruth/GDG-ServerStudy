package com.gdgBlog.gdgBlog.exception.user;

import com.gdgBlog.gdgBlog.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super ("USER_NOT_FOUND", message);;
    }
}