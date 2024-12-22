package com.gdgBlog.gdgBlog.domain.token.repository;

import com.gdgBlog.gdgBlog.domain.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    int deleteByRefreshToken(String refreshToken);
}
