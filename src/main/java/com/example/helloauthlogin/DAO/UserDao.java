package com.example.helloauthlogin.DAO;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDao {
    UserRegisterDTO findByUserId(String id);
    int saveUser(UserRegisterDTO user);
    int saveAuthorities(UserRegisterDTO user);
}
