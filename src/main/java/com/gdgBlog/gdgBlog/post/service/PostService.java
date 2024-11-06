package com.gdgBlog.gdgBlog.post.service;

import com.gdgBlog.gdgBlog.board.Board;
import com.gdgBlog.gdgBlog.board.repostirory.BoardRepository;
import com.gdgBlog.gdgBlog.post.Post;
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
    public PostDto createPost(PostDto postDto, Long userId, Long boardId) {

        User user = userRepository.findById(userId).orElseThrow();
        Board board = boardRepository.findById(boardId).orElseThrow();

        Post post = Post.of(postDto, user, board);
        post = postRepository.save(post);

        return PostDto.from(post);
    }
    @Transactional
    public PostDto updatePost(Long postId, PostDto dto, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        if (post.getUser()==user){
            post.updatePost(dto);
            postRepository.save(post);
            return PostDto.from(post);
        }else return null;
    }
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        postRepository.delete(post);
    }
}
