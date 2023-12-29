package com.ohgiraffers.pospos.menu.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandler {

    @GetMapping("controller-null")
    public String nullPointException(){
        String str = null;
        System.out.println(str.charAt(0));

        return "redirect:/";
    }
}
