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
    private String picture;
    private String password;
    private String number;

    public SessionUser() {
    }

    /*Entity -> Dto */
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.password = user.getPassword();
        this.number = user.getNumber();
    }

    public void encodePassword(String encode) {
        this.password = encode;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .password(password)
                .number(number)
                .role(Role.GUEST)
                .build();
    }
}