package com.ohgiraffers.pospos.menu.controller;

import com.ohgiraffers.pospos.menu.dto.MenuDTO;
import com.ohgiraffers.pospos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/menus/*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("menus")
    public ModelAndView selectAllMenu(ModelAndView mv){
        List<MenuDTO> menus = menuService.selectAllMenu();

        if(Objects.isNull(menus)){
            System.out.println("exception으로 대처한다");
        }
        mv.addObject("menus",menus);
        mv.setViewName("menus/allMenus");
        return mv;
    }
    @GetMapping("menu")
    public ModelAndView menu(ModelAndView mv){

        mv.setViewName("menus/menu");
        return mv;
    }
    @GetMapping("serachMenu")
    public ModelAndView searchMenu(ModelAndView mv, MenuDTO menuDTO){
        MenuDTO menu = menuService.searchMenu(menuDTO);
        mv.addObject("menus",menu);
        mv.setViewName("menus/allMenus");
        return mv;
    }


    @GetMapping("insert")
    public void insert(){};
    @PostMapping("insert")
    public ModelAndView insertMenu(ModelAndView mv,MenuDTO menuDTO){
        int result = menuService.insertMenu(menuDTO);

        if(result<=0){
            mv.addObject("insertMessage","등록 실패");
            mv.setViewName("/menus/failMessage");
        }else {
            mv.addObject("insertMessage","등록 성공");
            mv.setViewName("/menus/successMessage");
        }
        return mv;
    }

    @GetMapping("update")
    public void update(){};
    @PostMapping("update")
    public ModelAndView updateMenu(ModelAndView mv,MenuDTO menuDTO){
        int result = menuService.updateMenu(menuDTO);

        if(result<=0){
            mv.addObject("updateMessage","업데이트 실패");
            mv.setViewName("/menus/failMessage");
        }else {
            mv.addObject("updateMessage","업데이트 성공");
            mv.setViewName("/menus/successMessage");
        }
        return mv;
    }

    @GetMapping("delete")
    public void delete(){};
    @PostMapping("delete")
    public ModelAndView deleteMenu(ModelAndView mv, MenuDTO menuDTO){
        int result = menuService.deleteMenu(menuDTO);

        if(result<=0){
            mv.addObject("deleteMessage","삭제 실패");
            mv.setViewName("/menus/failMessage");
            return mv;
        }else {
            mv.addObject("deleteMessage","삭제 성공");
            mv.setViewName("/menus/successMessage");
            return mv;
        }
    }
}
