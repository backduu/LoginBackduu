package com.example.helloauthlogin.service.serviceImpl;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Resource(name="loginDAO")
    private LoginDao loginDAO;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterDTO saveSignUp(UserRegisterDTO userRegisterDTO) {

    }
}
