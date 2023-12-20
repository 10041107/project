package com.ohgiraffers.pospos.menu.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public String nullPointExceptionHandler(NullPointerException e){
        System.out.println("global에서 nullpoint를 처리");

        return "error/nullPointer";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        System.out.println("global에서 exception을 처리");

        return "error/default";
    }
}
