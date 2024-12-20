package com.gdgBlog.gdgBlog.exception.post;

import com.gdgBlog.gdgBlog.exception.BaseException;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException(String message) {
        super ("POST_NOT_FOUND", message);;
    }
}