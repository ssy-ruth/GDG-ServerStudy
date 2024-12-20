package com.gdgBlog.gdgBlog.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    AUTHENTICATION(403),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    NOT_FOUND(404),
    CONFLICT(409),
    NETWORK(502),
    SERVER_ERROR(500),
    UNKNOWN(500)
    ;

    int httpStatusCode;

    ErrorType(int httpStatusCode){
        this.httpStatusCode = httpStatusCode;
    }
}
