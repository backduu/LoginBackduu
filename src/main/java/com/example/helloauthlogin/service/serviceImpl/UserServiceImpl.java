package com.example.helloauthlogin.service.serviceImpl;

import com.example.helloauthlogin.DAO.UserDao;
import com.example.helloauthlogin.DTO.UserRegisterDTO;
import com.example.helloauthlogin.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterDTO saveSignUp(UserRegisterDTO userRegisterDTO) {
        return userDao.saveSignUp(userRegisterDTO);
    }
}
