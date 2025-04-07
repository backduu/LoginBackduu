package com.example.helloauthlogin.DAO;

import com.example.helloauthlogin.DTO.UserRegisterDTO;

public interface UserDao {
    UserRegisterDTO saveSignUp(UserRegisterDTO userRegisterDTO);
}
