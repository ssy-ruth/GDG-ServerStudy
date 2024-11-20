package com.gdgBlog.gdgBlog.user.controller;

import com.gdgBlog.gdgBlog.user.dto.CreateUserRequest;
import com.gdgBlog.gdgBlog.user.dto.LoginUserRequest;
import com.gdgBlog.gdgBlog.user.dto.UpdateUserRequest;
import com.gdgBlog.gdgBlog.user.dto.UserResponse;
import com.gdgBlog.gdgBlog.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
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

        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UpdateUserRequest request) {
        int flag = userService.update(request);

        return switch (flag) {
            case 200 -> new ResponseEntity<>(HttpStatus.OK);
            case 310 -> new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
            case 400 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        };
    }
}
