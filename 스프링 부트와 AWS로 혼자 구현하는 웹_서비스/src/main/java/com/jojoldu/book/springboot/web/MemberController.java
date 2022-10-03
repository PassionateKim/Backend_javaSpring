package com.jojoldu.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/members/new")
    public String signUpForm(Model model) {

        return "member/createMemberForm";
    }
}
