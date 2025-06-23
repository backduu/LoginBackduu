package com.example.helloauthlogin.DAO;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    UserRegisterDTO findByUserId(String id);
    int saveUser(UserRegisterDTO user);
}
