package com.gdgBlog.gdgBlog.user;

import com.gdgBlog.gdgBlog.post.Post;
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


//    public static User of(String name, String password, String salt, String email, Integer auth) {
//        User user = new User();
//        user.name=name;
//        user.password=password;
//        user.salt=salt;
//        user.email=email;
//        user.authority=auth;
//        return user;
//    }


    @Builder(builderMethodName="createUserBuilder")
    private User(String name, String password, String salt, Integer auth, String email) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.authority=auth;
        this.email=email;
    }

    public void updateName(String name){
        this.name=name;
    }

    public void updatePassword(String pwd){
        this.password=pwd;
    }
}
