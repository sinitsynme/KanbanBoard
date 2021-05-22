package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.service.UserProjectService;
import com.group_3.kanbanboard.service.UserService;
import com.group_3.kanbanboard.service.impl.UserProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projects/users")
public class UsersInProjectController {

    private final UserProjectService userProjectService;


    @Autowired
    public UsersInProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }


}
