package com.gdgBlog.gdgBlog.user.repository;

import com.gdgBlog.gdgBlog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<Object> findByName(String name);

    Boolean existsByName(String name);
}
