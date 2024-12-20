package com.gdgBlog.gdgBlog.exception;
import com.gdgBlog.gdgBlog.exception.post.PostNotFoundException;
import com.gdgBlog.gdgBlog.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //에러타입에 따른 base를 따로 만들기
    //성공했을 경우에 대한 응답 util을 따로 만들기도 한다
    //에러 타입과 에러 메시지를 인자로 받은 객체에서 메소드로 받기.
    // 1. UserNotFoundException 처리
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.NOT_FOUND,
                "USER_NOT_FOUND",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // 2. PostNotFoundException 처리
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.NOT_FOUND,
                "POST_NOT_FOUND",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // 3. 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}