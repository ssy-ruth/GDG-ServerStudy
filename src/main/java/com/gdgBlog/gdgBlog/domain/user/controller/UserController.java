package com.gdgBlog.gdgBlog.domain.user.controller;

import com.gdgBlog.gdgBlog.config.jwt.JwtFilter;
import com.gdgBlog.gdgBlog.domain.user.dto.CreateUserRequest;
import com.gdgBlog.gdgBlog.domain.user.dto.UpdateUserRequest;
import com.gdgBlog.gdgBlog.domain.user.dto.UserWithTokenResponse;
import com.gdgBlog.gdgBlog.domain.user.service.UserService;
import com.gdgBlog.gdgBlog.domain.user.dto.LoginUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<UserWithTokenResponse> signup(@RequestBody CreateUserRequest request){
        UserWithTokenResponse response = userService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserWithTokenResponse> login(@RequestBody LoginUserRequest request) {
        UserWithTokenResponse response = userService.login(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + response.getAccessToken());

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserWithTokenResponse> update(@RequestBody UpdateUserRequest request) {
        UserWithTokenResponse response = userService.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
