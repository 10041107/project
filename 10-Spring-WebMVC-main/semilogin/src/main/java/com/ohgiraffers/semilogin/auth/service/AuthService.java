package com.ohgiraffers.semilogin.auth.service;

import com.ohgiraffers.semilogin.auth.model.AuthDetails;
import com.ohgiraffers.semilogin.user.model.dto.LoginUserDTO;
import com.ohgiraffers.semilogin.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserDTO login = memberService.findByUserNickName(username);
        if(Objects.isNull(login)){
            System.out.println("존재 안함");
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }
        return new AuthDetails(login);
    }
}
