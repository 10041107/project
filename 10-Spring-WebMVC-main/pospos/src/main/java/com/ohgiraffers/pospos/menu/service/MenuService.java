package com.ohgiraffers.pospos.menu.service;

import com.ohgiraffers.pospos.menu.dto.MenuDTO;
import com.ohgiraffers.pospos.menu.model.MenuDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service //@Service는 내부에서 처리함
public class MenuService {
    private MenuDAO menuDAO;

    public MenuService(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public List<MenuDTO> selectAllMenu() {
        List<MenuDTO> menus = menuDAO.selectAllMenu();

        if(Objects.isNull(menus)){
            System.out.println("exception menu가 없음");
        }
        return menus;
    }


    public int insertMenu(MenuDTO menuDTO) {
        int result = menuDAO.insertMenu(menuDTO);
        if(result <= 0){
            System.out.println("실패");
        }else{
            System.out.println("성공");
        }
        return result;
    }

    public int updateMenu(MenuDTO menuDTO) {
        int result = menuDAO.updateMenu(menuDTO);
        if(result <= 0){
            System.out.println("실패");
        }else{
            System.out.println("성공");
        }
        return result;
    }

    public int deleteMenu(MenuDTO menuDTO) {
        int result = menuDAO.deleteMenu(menuDTO);
        if(result <= 0){
            System.out.println("실패");
        }else{
            System.out.println("성공");
        }
        return result;
    }

    public MenuDTO searchMenu(MenuDTO menuDTO) {
        MenuDTO menu = menuDAO.searchMenu(menuDTO);
        return menu;
    }
}
