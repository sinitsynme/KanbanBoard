package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.service.PrincipalService;
import com.group_3.kanbanboard.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/projects/users/role")
public class UserPageInProjectController {

    private final PrincipalService principalService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public UserPageInProjectController(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, UserService userService, PrincipalService principalService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.principalService = principalService;
    }
    @GetMapping
    public ModelAndView getPrincipalProfile(){
        ModelAndView modelAndView = new ModelAndView("setUserRoleInProject");
        modelAndView.addObject("principalDto", principalService.getPrincipal());
        return modelAndView;
    }


    @GetMapping("/userDetail")
    public String UserDetail(@RequestParam String userName, Model model) {
        UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(userName);
        model.addAttribute("user", userDetails);
        return "userDetail";

    }


}
