package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler  {
    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        DefaultOAuth2User loginUser = (DefaultOAuth2User)authentication.getPrincipal();
        Map<String, Object> attributes = loginUser.getAttributes();


        //email, oauth 받기
        String email = (String) attributes.get("email");
        String oauth = (String) attributes.get("oauth");
        Optional<User> foundUser = userRepository.findByEmail(email);

        //분기처리
        if (!foundUser.isEmpty()) {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", new SessionUser(foundUser.get()));
            response.sendRedirect("/");
        }
        else {
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("oauth", oauth);
            response.sendRedirect("/snsSignUp");
        }

    }
}
