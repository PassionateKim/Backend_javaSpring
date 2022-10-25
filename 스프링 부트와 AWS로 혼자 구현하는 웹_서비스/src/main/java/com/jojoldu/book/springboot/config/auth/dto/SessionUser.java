package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String password;
    private String oauth;


    public SessionUser() {
    }

    /*Entity -> Dto */
    public SessionUser(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.oauth = user.getOauth();
    }

    public void encodePassword(String encode) {
        this.password = encode;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .oauth(oauth)
                .email(email)
                .role(Role.GUEST)
                .build();
    }
}