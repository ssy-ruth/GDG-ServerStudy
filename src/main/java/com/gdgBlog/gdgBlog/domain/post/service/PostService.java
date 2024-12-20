package com.gdgBlog.gdgBlog.domain.post.service;

import com.gdgBlog.gdgBlog.domain.board.Board;
import com.gdgBlog.gdgBlog.domain.board.repostirory.BoardRepository;
import com.gdgBlog.gdgBlog.domain.post.Post;
import com.gdgBlog.gdgBlog.domain.post.dto.CreatePostRequest;
import com.gdgBlog.gdgBlog.domain.post.dto.PostDto;
import com.gdgBlog.gdgBlog.domain.post.repository.PostRepository;
import com.gdgBlog.gdgBlog.domain.user.User;
import com.gdgBlog.gdgBlog.domain.user.repository.UserRepository;
import com.gdgBlog.gdgBlog.exception.board.BoardNotFoundException;
import com.gdgBlog.gdgBlog.exception.post.PostNotFoundException;
import com.gdgBlog.gdgBlog.exception.user.UnauthorizedAccessException;
import com.gdgBlog.gdgBlog.exception.user.UserNotFoundException;
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
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(() -> new BoardNotFoundException("게시판을 찾을 수 없습니다."));

        Post post = request.toPostDto().toEntity(user,board);
        post = postRepository.save(post);

        return post.toPostDto();
    }

    @Transactional
    public PostDto updatePost(Long postId, CreatePostRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFoundException("게시물을 찾을 수 없습니다"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new UserNotFoundException("유저를 찾을 수 없습니다."));

        if (post.getUser()!=user){
            throw new UnauthorizedAccessException("게시물의 작성자가 아닙니다.");
        }
        post.updatePost(request.toPostDto());
        return post.toPostDto();
    }
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        postRepository.delete(post);
    }
}
