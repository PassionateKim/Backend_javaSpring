package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @GetMapping("/snsLogin")
    public String getLoginRedirect(HttpServletRequest request, Model model) throws IOException {

        String email = (String) request.getSession().getAttribute("email");

        model.addAttribute("email", email);

        return "sns-login";
    }


}
