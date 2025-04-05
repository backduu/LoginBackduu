package com.example.helloauthlogin.service;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


public interface UserService {

    /**
     * @author 백두현
     * @since 2025-04-05(005)
     * @param userRegisterDTO
     * @return void
     * @Descrption 회원가입 폼 유저 저장 메소드
     */
    void saveSignUp(UserRegisterDTO userRegisterDTO);

}
