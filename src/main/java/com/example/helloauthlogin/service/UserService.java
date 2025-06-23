package com.example.helloauthlogin.service;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


public interface UserService {
    UserRegisterDTO findByUserId(String id);
    void signUp(UserRegisterDTO userRegisterDTO);
}
