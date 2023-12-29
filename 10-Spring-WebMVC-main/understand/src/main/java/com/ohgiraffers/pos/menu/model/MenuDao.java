package com.ohgiraffers.pos.menu.model;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao {
    List<MenuDTO> selectAllMenu();

    int insertMenu(MenuDTO menuDTO);

    int deleteMenu(MenuDTO menuDTO);

    int updateMenu(MenuDTO menuDTO);

    Object searchMenu();
}
