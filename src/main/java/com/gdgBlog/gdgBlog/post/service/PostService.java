package com.gdgBlog.gdgBlog.post.service;

import com.gdgBlog.gdgBlog.board.Board;
import com.gdgBlog.gdgBlog.board.repostirory.BoardRepository;
import com.gdgBlog.gdgBlog.post.Post;
import com.gdgBlog.gdgBlog.post.dto.CreatePostRequest;
import com.gdgBlog.gdgBlog.post.dto.PostDto;
import com.gdgBlog.gdgBlog.post.repository.PostRepository;
import com.gdgBlog.gdgBlog.user.User;
import com.gdgBlog.gdgBlog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    BoardRepository boardRepository;


    @Transactional
    public PostDto createPost(CreatePostRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow();

        Post post = request.toPostDto().toEntity(user,board);//(postDto, user, board);
        post = postRepository.save(post);

        return post.toPostDto();
    }
    @Transactional
    public PostDto updatePost(Long postId, CreatePostRequest request) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        if (post.getUser()==user){
            post.updatePost(request.toPostDto());
            //postRepository.save(post);
            return post.toPostDto();
        }else return null;
    }
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        postRepository.delete(post);
    }
}
