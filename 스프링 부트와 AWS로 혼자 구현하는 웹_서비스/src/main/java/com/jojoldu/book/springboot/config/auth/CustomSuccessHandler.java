package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
public class CustomSuccessHandler implements AuthenticationSuccessHandler  {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("로그인 성공!!");
        DefaultOAuth2User loginUser = (DefaultOAuth2User)authentication.getPrincipal();
        Map<String, Object> attributes = loginUser.getAttributes();
        for (String s : attributes.keySet()) {
            System.out.println(s + " = " + attributes.get(s));
        }
    }
}
