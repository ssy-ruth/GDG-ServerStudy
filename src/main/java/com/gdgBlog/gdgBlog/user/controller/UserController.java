package com.gdgBlog.gdgBlog.user.controller;

import com.gdgBlog.gdgBlog.user.dto.CreateUserRequest;
import com.gdgBlog.gdgBlog.user.dto.LoginUserRequest;
import com.gdgBlog.gdgBlog.user.dto.UserResponse;
import com.gdgBlog.gdgBlog.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody CreateUserRequest request){
        UserResponse response = userService.signup(request.getEmail(), request.getName(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginUserRequest request) {
        UserResponse response = userService.login(request.getEmail(), request.getPassword());

        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //닉네임 수정, 계정 삭제
}
