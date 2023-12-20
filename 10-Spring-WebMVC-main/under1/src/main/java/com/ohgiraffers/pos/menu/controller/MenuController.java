package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.RegistDTO;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public ModelAndView selectAllMenu(ModelAndView mv){ //모든 메뉴 조회

        List<MenuDTO> menus = menuService.selectAllMenu(); //메뉴가 여러개 있으니깐 List사용

        if(Objects.isNull(menus)){ //menus에 값이 null일때
            throw new NullPointerException(); //NullPointerException을 던져줌

        }else { //null 아닐때
            mv.addObject("menus", menus); //view에 전달할 값 설정
            mv.setViewName("menu/allMenus"); //응답할 view이름
            return mv; //mv에 반환
        }
    }

    @GetMapping("/search")
    public ModelAndView searchMenu(ModelAndView mv,@RequestParam int code){ //code에 해당하는 값만 조회

        if(code == 0){ //코드가 0일시
            System.out.println("메뉴 코드는 0일 수 없습니다.");
            mv.addObject("message", "메뉴 코드는 0이 될 수 없습니다.");
            mv.setViewName("error/errorPage");
            return mv;
        }

        MenuDTO menu = menuService.searchMenu(code); //하나만 조회하는 것이니 List 사용안함

        if(Objects.isNull(menu)){
            throw new NullPointerException();
        }else {
            mv.addObject("menu", menu);
            mv.setViewName("result/searchResult");
            return mv;
        }
    }

    @PostMapping("/auth")
    public  String goToAdminPage(){
        return "/adminPage";
    }

    @PostMapping("/regist") //등록하기
    public String registMenu(@RequestParam Map<String, String> parameters){
        //Map<key, value>로 값을 집어넣을려고 함

        String name= parameters.get("name"); //<name, >
        int price = Integer.parseInt(parameters.get("price")); //price는 int라 형변환 시켜줌
        int categoryCode = Integer.parseInt(parameters.get("categoryCode"));
        String status = parameters.get("status");

        RegistDTO regist = new RegistDTO();
        regist.setName(name); //값 넣어줌
        regist.setPrice(price);
        regist.setCategoryCode(categoryCode);
        regist.setStatus(status);

        int result = menuService.registMenu(regist);

        if(result > 0){
            return "result/registSuccess";
        }else {
            return "result/registFail";
        }
    }

    @PostMapping("/modify")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters){

        int code = Integer.parseInt(parameters.get("code"));
        String name = parameters.get("name");
        int price = Integer.parseInt(parameters.get("price"));
        int categoryCode = Integer.parseInt(parameters.get("categoryCode"));
        String status = parameters.get("status");

        MenuDTO modify = new MenuDTO();
        modify.setCode(code);
        modify.setName(name);
        modify.setPrice(price);
        modify.setCategoryCode(categoryCode);
        modify.setStatus(status);

        int result = menuService.modifyMenu(modify);

        if(result > 0){
            model.addAttribute("message", "수정에 성공하였습니다.");
            return "result/modifyResult";
        }else {
            model.addAttribute("message", "수정에 실패하셨습니다.");
            return "result/modifyResult";
        }
    }

    @PostMapping("/delete")
    public ModelAndView deleteMenu(ModelAndView mv, MenuDTO menuDTO){

        int result = menuService.deleteMenu(menuDTO);

        if(result <= 0){
            System.out.println("삭제에 실패하셨습니다.");
            mv.addObject("message", "삭제에 실패하셨습니다.");
            mv.setViewName("result/deleteResult");
            return mv;
        }else {

            mv.addObject("message", menuDTO.getCode() + "번 메뉴를 삭제하셨습니다.");
            mv.setViewName("result/deleteResult");
            return mv;
        }

    }
}