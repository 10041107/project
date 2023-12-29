package com.ohgiraffers.pospos.menu.model;

import com.ohgiraffers.pospos.menu.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper  //sql과 자동으로 연결해주는 어노테이션
public interface MenuDAO {
    List<MenuDTO> selectAllMenu();


    int insertMenu(MenuDTO menuDTO);

    int updateMenu(MenuDTO menuDTO);

    int deleteMenu(MenuDTO menuDTO);

    MenuDTO searchMenu(MenuDTO menuDTO);
}
