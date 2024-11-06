package com.gdgBlog.gdgBlog.post.controller;

import com.gdgBlog.gdgBlog.post.dto.CreatePostRequest;
import com.gdgBlog.gdgBlog.post.dto.CreatePostResponse;
import com.gdgBlog.gdgBlog.post.dto.PostDto;
import com.gdgBlog.gdgBlog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        Long userId = request.getUserId();
        Long boardId = request.getBoardId();

        PostDto postDto = PostDto.convertToPostDto(request);
        PostDto createdPostDto = postService.createPost(postDto, userId, boardId);

        CreatePostResponse response = CreatePostResponse.fromPost(createdPostDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<CreatePostResponse> updatePlan(@PathVariable Long postId, @RequestBody CreatePostRequest request) {
        PostDto postDto = PostDto.convertToPostDto(request);
        Long userId = request.getUserId();

        PostDto updatedPostDto = postService.updatePost(postId, postDto, userId);
        CreatePostResponse response = CreatePostResponse.fromPost(updatedPostDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //유저검사 해야함
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
