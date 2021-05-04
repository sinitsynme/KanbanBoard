package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.rest.UserController;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class MainUserController {


    @GetMapping("/xxx")
    public String getUsers() {

    }


}
