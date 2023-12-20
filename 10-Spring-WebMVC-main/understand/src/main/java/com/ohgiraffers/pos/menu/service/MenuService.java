package com.ohgiraffers.pos.menu.service;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.model.MenuDao;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Service
public class MenuService {

    private MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<MenuDTO> selectAllMenu() {
        List<MenuDTO> menus = menuDao.selectAllMenu();

        if(Objects.isNull(menus)){
            System.out.println("exeception menus가 없네요");
        }
        return menus;
    }

    public int insertMenu(MenuDTO menuDTO){
        int result = menuDao.insertMenu(menuDTO);
        if(result <= 0){
            System.out.println("실패");
        }else{
            System.out.println("성공");
        }
        return result;
    }

    public int updateMenu(MenuDTO menuDTO) {
        int result = menuDao.updateMenu(menuDTO);
        if(result <= 0){
            System.out.println("실패");
        }else{
            System.out.println("성공");
        }
        return result;
    }

    public int deleteMenu(MenuDTO menuDTO) {
        int result = menuDao.deleteMenu(menuDTO);
        if(result <= 0){
            System.out.println("실패");
        }else{
            System.out.println("성공");
        }

        return result;
    }

    public MenuDTO searchMenu() {
        MenuDTO menu = (MenuDTO) menuDao.searchMenu();

        if(Objects.isNull(menu)){
            System.out.println("exeception menus가 없네요");
        }
        return menu;
    }
}
