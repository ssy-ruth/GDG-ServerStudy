package com.gdgBlog.gdgBlog.post.repository;

import com.gdgBlog.gdgBlog.board.Board;
import com.gdgBlog.gdgBlog.post.Post;
import com.gdgBlog.gdgBlog.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByBoard(Pageable pageable, Board board);

    @Query("SELECT p FROM Post p WHERE p.board.authority >= :auth AND " +
            "(LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Post> findByKeyword(@Param("keyword") String keyword, @Param("auth")Integer auth, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);
}
