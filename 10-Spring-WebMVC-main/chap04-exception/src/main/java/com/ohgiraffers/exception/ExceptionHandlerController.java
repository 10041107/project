package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointerExceptionTest(){
        //thorw NullPointerException을 하면 최종적으로 was서버로 가는데 was서버는 처리를 못함
        String str = null;
        System.out.println(str.charAt(0));
        //익셉션이 프로그램이 받아줘야되는데 오류나서 받질 못하고 던져버려서 오류가 남
        return "/main";
    }

    @ExceptionHandler(NullPointerException.class) //얘가 오류를 처리함 우선권을 가지고 있음 여기에 없을시 GlobalExceptionHandler로 넘어감
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("controller 레벨의 Exception 처리");
        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userException() throws MemeberRegistException{
        boolean check = true;
        if(check){
            throw new MemeberRegistException("입사가 불가능합니다.");
        }
        return "/";
    }

    @ExceptionHandler(MemeberRegistException.class)
    public String memberRegistExceptionHandler(MemeberRegistException e){
        System.out.println("controller 레벨의 exception 처리");
        return "error/memberRegist";
    }
}
