package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project/users")
public class UsersListOnProjectController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public UsersListOnProjectController(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping
    public String usersList(Model model) {
        model.addAttribute("projectUsers" , userService.getAllUsers());
        return "usersOnProjectList";
    }


    @GetMapping("/userDetail")
    public String UserDetail(@RequestParam String userName, Model model) {
        UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(userName);
        model.addAttribute("user", userDetails);
        return "userDetail";

    }

}
