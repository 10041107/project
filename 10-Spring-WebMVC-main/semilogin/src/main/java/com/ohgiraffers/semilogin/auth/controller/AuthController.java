/*
AuthController는 /auth/login 및 /auth/fail에 대한 요청을 처리합니다.
로그인 실패 시 실패 메시지를 전달합니다.
 */

package com.ohgiraffers.semilogin.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // "/auth/login" 경로에 대한 GET 요청 처리
    @GetMapping("/login")
    public void login(){

    }

    // "/auth/fail" 경로에 대한 GET 요청 처리
    @GetMapping("/fail")
    public ModelAndView loginFail(@RequestParam String message, ModelAndView mv){
        mv.addObject("message", message);
        mv.setViewName("/auth/fail");
        return mv;
    }
}
