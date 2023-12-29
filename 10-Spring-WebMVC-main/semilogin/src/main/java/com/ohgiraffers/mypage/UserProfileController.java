package com.ohgiraffers.mypage;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    @Autowired
    private UserService userService;

    // 프로필 수정 폼 요청
    @GetMapping("/edit-profile")
    public ModelAndView editProfile(ModelAndView mv) {
        // 현재 사용자 정보를 가져와 폼에 전달
        User currentUser = userService.getCurrentUser();
        mv.addObject("user", currentUser);
        mv.setViewName("/user/edit-profile");
        return mv;
    }

    // 프로필 수정 폼 제출
    @PostMapping("/edit-profile")
    public String submitEditProfile(@ModelAttribute User updatedUser) {
        // 서비스 레이어를 통해 사용자 정보 업데이트
        userService.updateUserProfile(updatedUser);
        return "redirect:/user/my-page";
    }

    // 비밀번호 수정 폼 요청
    @GetMapping("/change-password")
    public ModelAndView changePassword(ModelAndView mv) {
        mv.setViewName("/user/change-password");
        return mv;
    }

    // 비밀번호 수정 폼 제출
    @PostMapping("/change-password")
    public String submitChangePassword(@RequestParam String currentPassword, @RequestParam String newPassword) {
        // 서비스 레이어를 통해 비밀번호 유효성 확인 및 업데이트
        userService.changeUserPassword(currentPassword, newPassword);
        return "redirect:/user/my-page";
    }

//    // MySQL 정보 수정 폼 요청
//    @GetMapping("/edit-mysql-info")
//    public ModelAndView editMysqlInfo(ModelAndView mv) {
//        mv.setViewName("/user/edit-mysql-info");
//        return mv;
//    }

    // MySQL 정보 수정 폼 제출
//    @PostMapping("/edit-mysql-info")
//    public String submitEditMysqlInfo(@RequestParam String newDatabaseUrl, @RequestParam String newUsername, @RequestParam String newPassword) {
//        // 서비스 레이어를 통해 MySQL 연결 정보 업데이트
//        userService.updateMysqlConnectionInfo(newDatabaseUrl, newUsername, newPassword);
//        return "redirect:/user/my-page";
//    }
//}
