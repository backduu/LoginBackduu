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

//    @Override
//    public UserRegisterDTO saveSignUp(UserRegisterDTO userRegisterDTO) {
//        int result = sqlSession.insert("loginDAO.insert_saveSignUp", userRegisterDTO);
//
//        if(result > 0) {
//            return userRegisterDTO;
//        } else {
//            UserRegisterDTO user = sqlSession.selectOne("loginDAO.select_findByUserName", userRegisterDTO);
//            throw new LoginBackduuException(ErrorCode.SAVE_ERROR);
//        }
//    }

    @Override
    public UserRegisterDTO findByUserId(String id) {
        UserRegisterDTO userRegisterDTO = sqlSession.selectOne("UserDao.findByUserId", id);
        if(userRegisterDTO == null){
            throw new LoginBackduuException(ErrorCode.NO_DEVELOPER);
        }
        return userRegisterDTO;
    }
}
