package com.gdgBlog.gdgBlog.domain.board.service;

import com.gdgBlog.gdgBlog.domain.board.Board;
import com.gdgBlog.gdgBlog.domain.board.dto.BoardRequest;
import com.gdgBlog.gdgBlog.domain.board.dto.BoardResponse;
import com.gdgBlog.gdgBlog.domain.board.dto.CreateBoardRequest;
import com.gdgBlog.gdgBlog.domain.board.repostirory.BoardRepository;
import com.gdgBlog.gdgBlog.domain.user.User;
import com.gdgBlog.gdgBlog.domain.user.repository.UserRepository;
import com.gdgBlog.gdgBlog.exception.board.BoardNotFoundException;
import com.gdgBlog.gdgBlog.exception.user.UnauthorizedAccessException;
import com.gdgBlog.gdgBlog.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    UserRepository userRepository;
    BoardRepository boardRepository;

    public BoardResponse createBoard(CreateBoardRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        if (user.getAuthority()!=0){
            throw new UnauthorizedAccessException("게시판 생성 권한이 없습니다.");
        }
        Board board = request.toEntity();
        board = boardRepository.save(board);

        return board.toDto();
    }

    public void delete(BoardRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        if (user.getAuthority()!=0){
            throw new UnauthorizedAccessException("게시판 삭제 권한이 없습니다.");
        }
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(()->new BoardNotFoundException("게시판을 찾을 수 없습니다."));
        boardRepository.delete(board);
    }

}
