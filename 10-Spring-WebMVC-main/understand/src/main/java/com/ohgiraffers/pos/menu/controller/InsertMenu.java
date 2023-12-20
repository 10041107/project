package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller //요청을 받음
@RequestMapping("/insert") //요청을 처리하는 방식을 고름
public class InsertMenu {
    @Autowired //자동으로 의존성 주입해줌
    private MenuService menuService;
    // localhost:8080/insert
    @GetMapping
    public String insertMenu(){
        return "menu/insertMenu";
    };
    // localhost:8080/insert/insert-menu
    @PostMapping("/insert-menu")
    public String insertMenu(MenuDTO menuDTO){
        menuService.insertMenu(menuDTO);

        return "redirect:/";
        //static의 index.html로 redirect
    }
//    @ExceptionHandler(NullPointerException.class)
//    public String nullPointerExceptionHandler(NullPointerException e){
//        System.out.println(e.getClass());
//        return "error/default";
//    }
}
