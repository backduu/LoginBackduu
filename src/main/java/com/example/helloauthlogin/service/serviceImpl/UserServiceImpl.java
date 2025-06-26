package com.example.helloauthlogin.service.serviceImpl;

import com.example.helloauthlogin.DAO.UserDao;
import com.example.helloauthlogin.DTO.UserRegisterDTO;
import com.example.helloauthlogin.exception.LoginBackduuException;
import com.example.helloauthlogin.service.UserService;
import com.example.helloauthlogin.type.ErrorCode;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserRegisterDTO findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

    public void signUp(UserRegisterDTO userRegisterDTO) {
        if(userDao.findByUserId(userRegisterDTO.getId()) != null){
            throw new LoginBackduuException(ErrorCode.EXIST_ID);
        }

        // 비밀번호 암호화
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        int a = userDao.saveUser(userRegisterDTO);
        // 유저 저장 완료되면 권한 관리 위한 삽입 실행
        if(a == 1) {
            Map<String, Object> map = new HashMap<>();
            userRegisterDTO.setAuthority(List.of("ROLE_USER", "ROLE_ADMIN"));

            userDao.saveAuthorities(userRegisterDTO);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>  " + a);
    }
}
