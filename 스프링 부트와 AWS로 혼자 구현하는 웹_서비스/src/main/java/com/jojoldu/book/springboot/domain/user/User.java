package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String naverId;

    @Column
    private String googleId;

    @Column
    private String registerId;


    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String number;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String naverId, String googleId, String email, Role role, String password, String number, String registerId) {
        this.name = name;
        this.naverId = naverId;
        this.googleId = googleId;
        this.email = email;
        this.role = role;
        this.password = password;
        this.registerId = registerId;
        this.number = number;
    }

    public User update(String name) {
        this.name = name;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
