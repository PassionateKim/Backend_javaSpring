package com.jojoldu.book.springboot.service.user;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(SessionUser sessionUser) {
        /* 암호화 */
        sessionUser.encodePassword(encoder.encode(sessionUser.getPassword()));
        return userRepository.save(sessionUser.toEntity()).getId();
    }
}
