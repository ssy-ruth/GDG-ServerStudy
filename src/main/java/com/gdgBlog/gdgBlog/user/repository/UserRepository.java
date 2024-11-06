package com.gdgBlog.gdgBlog.user.repository;

import com.gdgBlog.gdgBlog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<Object> findByNickname(String nickname);
}
