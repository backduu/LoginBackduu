package com.example.helloauthlogin.DAO.daoImpl;

import com.example.helloauthlogin.DAO.UserDao;
import com.example.helloauthlogin.DTO.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final SqlSession sqlSession;

    @Override
    public UserRegisterDTO saveSignUp(UserRegisterDTO userRegisterDTO) {
        int result = sqlSession.insert("loginDAO.insert_saveSignUp", userRegisterDTO);

        return
    }
}
