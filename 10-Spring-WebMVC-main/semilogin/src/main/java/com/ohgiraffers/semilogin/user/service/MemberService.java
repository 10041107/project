package com.ohgiraffers.semilogin.user.service;

import com.ohgiraffers.semilogin.user.dao.UserMapper;
import com.ohgiraffers.semilogin.user.model.dto.LoginUserDTO;
import com.ohgiraffers.semilogin.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class MemberService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder encoder;

    public LoginUserDTO findByUserNickName(String userNickName){

        LoginUserDTO login = userMapper.findByUserNickName(userNickName);
        System.out.println(login);
        if(!Objects.isNull(login)){
            System.out.println("있");
            return login;
        }else{
            System.out.println("없");
            return null;
        }
    }

    @Transactional
    public int regist(SignupDTO signupDTO){
        signupDTO.setPassword(encoder.encode(signupDTO.getPassword()));
        int result = userMapper.regist(signupDTO);

        return result;
    }
}
