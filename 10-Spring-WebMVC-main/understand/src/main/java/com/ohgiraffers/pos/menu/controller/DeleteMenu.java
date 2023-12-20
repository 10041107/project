package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("delete")
public class DeleteMenu {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String deleteMenu(){
        return "menu/deleteMenu";
    }

    @PostMapping("delete-menu")
    public String deleteMenu(MenuDTO menuDTO){
        menuService.deleteMenu(menuDTO);

        return "redirect:/";
    }
}
