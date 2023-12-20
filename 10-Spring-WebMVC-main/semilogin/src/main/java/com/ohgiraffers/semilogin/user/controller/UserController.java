package com.ohgiraffers.semilogin.user.controller;

import com.ohgiraffers.semilogin.user.model.dto.SignupDTO;
import com.ohgiraffers.semilogin.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public void signup(){

    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute SignupDTO signupDTO, ModelAndView mv){

        int result = memberService.regist(signupDTO);

        String message;
        if(result>0){
            message = "회원가입 완료";
            mv.setViewName("auth/clearSignup");
        }else {
            message = "회원가입 실패";
            mv.setViewName("auth/login");
        }
        System.out.println(result);
        mv.addObject("message",message);

        return mv;
    }

}
