package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //요청을 받아줌
@RequestMapping("/*") //@RequestMapping : 요청 받을 방식을 정함 ("/*")모든 방식
public class interceptorTestController {

    @PostMapping("stopwatch") //stopwatch에 매핑을 보내준다
    public String handlerMethod() throws InterruptedException/*Interrupted 예외를 던져준다?*/{
        //interrupt는 특정 Thread에게 작업을 멈춰 달라고 요청하는 형태
        System.out.println("핸들러 메소드 호출함");
        Thread.sleep(1000); //1초의 대기

        return "result";
        //result.html로 반환한다
        //1번 실행
    }
}
