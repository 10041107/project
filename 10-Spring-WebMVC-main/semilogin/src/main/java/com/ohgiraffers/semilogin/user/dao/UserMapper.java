package com.ohgiraffers.semilogin.user.dao;

import com.ohgiraffers.semilogin.user.model.dto.LoginUserDTO;
import com.ohgiraffers.semilogin.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Member;

@Mapper
public interface UserMapper {
    LoginUserDTO findByUserNickName(String userNickName);

    int regist(SignupDTO signupDTO);

}
