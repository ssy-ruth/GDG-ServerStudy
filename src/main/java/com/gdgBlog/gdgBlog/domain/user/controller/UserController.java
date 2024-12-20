package com.gdgBlog.gdgBlog.domain.user.controller;

import com.gdgBlog.gdgBlog.domain.user.dto.CreateUserRequest;
import com.gdgBlog.gdgBlog.domain.user.dto.UpdateUserRequest;
import com.gdgBlog.gdgBlog.domain.user.dto.UserResponse;
import com.gdgBlog.gdgBlog.domain.user.service.UserService;
import com.gdgBlog.gdgBlog.domain.user.dto.LoginUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody CreateUserRequest request){
        UserResponse response = userService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginUserRequest request) {
        UserResponse response = userService.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UpdateUserRequest request) {
        UserResponse response = userService.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
