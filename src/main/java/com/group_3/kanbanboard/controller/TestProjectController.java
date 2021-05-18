package com.group_3.kanbanboard.controller;


import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.repository.ProjectRepository;
import com.group_3.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("users/{userName}/projects/{id}")
public class TestProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestProjectController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String testProject(@PathVariable String userName, @PathVariable UUID id, Model model) {

        UserEntity user = userRepository.findByUsername(userName).orElseGet(UserEntity::new);

        ProjectEntity project = projectRepository.findById(id).orElseGet(ProjectEntity::new);

        model.addAttribute("user", user);
        model.addAttribute("project", project);

        return "userList";
    }
}
