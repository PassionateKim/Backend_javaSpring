package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/new")
    public String signUpForm(HttpServletRequest httpServletRequest, Model model) {
        //session oauth 초기화
        httpServletRequest.getSession().setAttribute("oauth", null);
        model.addAttribute("sessionUser", new SessionUser());
        return "user/createUserForm";
    }

    @PostMapping("/users/new")
    public String signUp(@Valid SessionUser sessionUser, BindingResult result, HttpServletRequest httpServletRequest) {
        if(result.hasErrors()) {
            System.out.println("에러발생!!!!!!!");
        }

        String oauth = (String) httpServletRequest.getSession().getAttribute("oauth");

        //oauth인지 체크
        if(!(oauth == null)) {
            sessionUser.setOauth(oauth);
        }

        userService.join(sessionUser);
        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("user", sessionUser);

        return "redirect:/";
    }

}
