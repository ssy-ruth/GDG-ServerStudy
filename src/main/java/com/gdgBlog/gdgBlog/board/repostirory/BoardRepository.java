package com.gdgBlog.gdgBlog.board.repostirory;

import com.gdgBlog.gdgBlog.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
