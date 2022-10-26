package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @GetMapping("/snsSignUp")
    public String snsLoginRedirect(HttpServletRequest request, Model model) throws IOException {

        String email = (String) request.getSession().getAttribute("email");
        model.addAttribute("email", email);

        return "sns-signup";
    }

    @GetMapping("/logins")
    public String createLoginForm() {

        return "createLoginForm";
    }

    @PostMapping("/logins")
    @ResponseBody
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {

        /**
         * 회원 있는지 체크하는 로직
         */
        List<User> foundUsers = userRepository.findAll();
        for (User foundUser : foundUsers) {
            String foundEmail = foundUser.getEmail();

            if(foundEmail.equals(email)) {
                String encodedPassword = foundUser.getPassword();
                if(encoder.matches(password, encodedPassword)) {
                    return "로그인 성공";
                }
            }
        }

        return "로그인 실패";
    }


}
