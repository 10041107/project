package com.ohgiraffers.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Controller에서 문제가 발생하면 public class GlobalExceptionHandler{}에 문제를 전달시켜주는 역할
//우리 서버에서 발생되는 모든 에러를 처리해줌 Controllor 뿐만 아니라
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class) //익셉션을 정확하게 핸들링해줘야지 더 맞는방식임
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("global 레벨의 Exception 처리");

        return "error/nullPointer";
    }

    @ExceptionHandler(MemeberRegistException.class)
    public String userExceptionHandler(Model model, MemeberRegistException exception){
        System.out.println("Global 레벨이 exception 처리");
        model.addAttribute("exception",exception);
        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String nullPointerExceptionHandler(Exception e){
        System.out.println("exception 발생발생함");
        return "error/default";
    }
}
