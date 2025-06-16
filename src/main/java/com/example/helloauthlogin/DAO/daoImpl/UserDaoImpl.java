package com.example.helloauthlogin.DAO.daoImpl;

import com.example.helloauthlogin.DAO.UserDao;
import com.example.helloauthlogin.DTO.UserRegisterDTO;
import com.example.helloauthlogin.exception.LoginBackduuException;
import com.example.helloauthlogin.type.ErrorCode;
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


        // 여기서 사용자 체크해서 이미 있는 아이디, 비밀번호, 계정인지 확인해보자.
        if(result > 0) {
            return userRegisterDTO;
        } else {
            UserRegisterDTO user = sqlSession.selectOne("loginDAO.select_findByUserName", userRegisterDTO);
            throw new LoginBackduuException(ErrorCode.SAVE_ERROR);
        }
    }
}
