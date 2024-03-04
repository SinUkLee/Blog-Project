package com.example.myblog.service;

import com.example.myblog.entity.Role;
import com.example.myblog.entity.User;
import com.example.myblog.dto.JoinDto;
import com.example.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void join(JoinDto joinDto) {

        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());

        if (isUser) {
            return;
        }
        User user = new User();
        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setEmail(joinDto.getEmail());
        user.setRole(Role.USER);

        userRepository.save(user);
    }
}
