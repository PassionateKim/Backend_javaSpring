package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/new")
    public String signUpForm() {
        return "user/createUserForm";
    }

    @PostMapping("/users/new")
    public String signUp(SessionUser sessionUser) {


        userService.join(sessionUser);

        return "redirect:/";
    }
}
