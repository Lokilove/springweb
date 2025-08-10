package com.mvc.web.controller;

import com.mvc.web.bean.UserFormBean;
import com.mvc.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    /**
     * ユーザーサービス
     */
    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/editProfile")
    public String showEditProfile(Model model, Principal principal) {
        // principalからユーザー情報取得、userFormにセット
        UserFormBean userForm = userService.getUser(principal.getName());
        model.addAttribute("userForm", userForm);
        return "user/editProfile"; // 部分JSPだけ返す
    }

    @RequestMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UserFormBean userFormBean, Model model) {
        boolean success = userService.updateUser(userFormBean);
        model.addAttribute("userForm", userFormBean);
        if (success) {
            model.addAttribute("message", "更新成功");
        } else {
            model.addAttribute("error", "更新失敗");
        }
        return "user/editProfile"; // 更新後の部分JSP返す（Ajax成功時の置き換え用）
    }
}
