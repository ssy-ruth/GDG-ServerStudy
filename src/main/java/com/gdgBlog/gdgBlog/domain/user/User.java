package com.gdgBlog.gdgBlog.domain.user;

import com.gdgBlog.gdgBlog.domain.post.Post;
import com.gdgBlog.gdgBlog.domain.user.dto.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private Integer authority;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    @Builder(builderMethodName="createUserBuilder")
    private User(String name, String password, String salt, Integer auth, String email) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.authority=auth;
        this.email=email;
    }

    public void update(UpdateUserRequest request) {
        this.name = request.getName();
        this.password = request.getPassword();
    }
}
