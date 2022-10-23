package com.jojoldu.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@Controller
public class LoginController {

    @GetMapping("/snsLogin")
    public String getLoginRedirect(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        String email = (String) request.getSession().getAttribute("email");
        System.out.println("email = " + email);

        model.addAttribute("email", email);

        return "sns-login";
    }

}
