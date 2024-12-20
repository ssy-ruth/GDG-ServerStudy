package com.gdgBlog.gdgBlog.domain.post.controller;

import com.gdgBlog.gdgBlog.domain.post.dto.CreatePostRequest;
import com.gdgBlog.gdgBlog.domain.post.dto.CreatePostResponse;
import com.gdgBlog.gdgBlog.domain.post.dto.PostDto;
import com.gdgBlog.gdgBlog.domain.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService=postService;
    }
    @PostMapping
    public ResponseEntity<CreatePostResponse> create(@RequestBody CreatePostRequest request) {
        PostDto createdPostDto = postService.createPost(request);

        CreatePostResponse response = CreatePostResponse.toDto(createdPostDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<CreatePostResponse> update(@PathVariable Long postId, @RequestBody CreatePostRequest request) {
        PostDto updatedPostDto = postService.updatePost(postId, request);
        CreatePostResponse response = CreatePostResponse.toDto(updatedPostDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //유저검사 해야함
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
