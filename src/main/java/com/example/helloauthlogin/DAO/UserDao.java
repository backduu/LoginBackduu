package com.example.helloauthlogin.DAO;

import com.example.helloauthlogin.DTO.UserRegisterDTO;

public interface UserDao {
    UserRegisterDTO findByUserId(String id);
    //UserRegisterDTO saveSignUp(UserRegisterDTO userRegisterDTO);
}
