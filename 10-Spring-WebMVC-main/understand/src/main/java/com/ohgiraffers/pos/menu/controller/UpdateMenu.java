package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/update")
public class UpdateMenu {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String updateMenu(){
        return "menu/updateMenu";
    };
    @PostMapping("update-menu")
    public String updateMenu(MenuDTO menuDTO){
        menuService.updateMenu(menuDTO);

        return "redirect:/";
    }
}
