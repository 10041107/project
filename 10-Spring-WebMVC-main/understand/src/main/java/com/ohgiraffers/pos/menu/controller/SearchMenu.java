package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("search")
public class SearchMenu {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public ModelAndView searchMenu(ModelAndView mv){
        MenuDTO menu = (MenuDTO) menuService.searchMenu();

        if(Objects.isNull(menu)){
            System.out.println("exception으로 대체한다.");
        }
        mv.addObject("search",menu);
        mv.setViewName("menu/searchMenu");
        return mv;
    }
}
