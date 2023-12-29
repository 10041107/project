/*
컨트롤러 (IndexController 및 AuthController 클래스):

IndexController는 기본 경로 및 특정 경로에 대한 요청을 처리합니다.
주로 페이지 이동을 담당합니다.
 */

package com.ohgiraffers.semilogin.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    // 기본 경로("/")에 대한 요청 처리
    @GetMapping
    public String root(){
        return "index";
    }

    // "/admin/page" 경로에 대한 요청 처리
    @GetMapping("/admin/page")
    public ModelAndView admnin(ModelAndView mv){
        mv.setViewName("admin/admin");
        return mv;
    }

    // "/user/page" 경로에 대한 요청 처리
    @GetMapping("/user/page")
    public ModelAndView user(ModelAndView mv){
        mv.setViewName("/user/user");
        return mv;
    }
}
