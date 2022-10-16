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
    private String naverId;
    private String googleId;
    private String password;
    private String number;


    public SessionUser() {
    }

    /*Entity -> Dto */
    public SessionUser(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.naverId = user.getNaverId();
        this.googleId = user.getGoogleId();
        this.number = user.getNumber();
    }

    public void encodePassword(String encode) {
        this.password = encode;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .naverId(naverId)
                .googleId(googleId)
                .password(password)
                .number(number)
                .role(Role.GUEST)
                .build();
    }
}