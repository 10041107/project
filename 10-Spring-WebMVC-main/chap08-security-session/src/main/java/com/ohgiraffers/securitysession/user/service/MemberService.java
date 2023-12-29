package com.ohgiraffers.securitysession.user.service;

import com.ohgiraffers.securitysession.user.dao.UserMapper;
import com.ohgiraffers.securitysession.user.model.dto.LoginUserDTO;
import com.ohgiraffers.securitysession.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service //사용자의 요청을 어떻게 처리할지 방식을 정함
public class MemberService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder encoder; //PasswordEncoder는 비밀번호 암호화

    @Transactional //롤백을 가능하게 해주는 일종의 보험역할
    public int regist(SignupDTO signupDTO){
        signupDTO.setUserPass(encoder.encode(signupDTO.getUserPass()));
        int result = userMapper.regist(signupDTO);

        return result;
    }

    public LoginUserDTO findByUserName(String username) {

        LoginUserDTO login = userMapper.findByUserName(username);
        System.out.println(login);
        if(!Objects.isNull(login)){
            return login;
        }else {
            return null;
        }
    }
}
