package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.service.ModelViewTaskService;
import com.group_3.kanbanboard.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("users/profile/projects/{projectId}/releases/{releaseId}/tasks/")
public class ModelViewTaskController {

    private final ModelViewTaskService modelViewTaskService;



    @Autowired
    public ModelViewTaskController(ModelViewTaskService modelViewTaskService) {
        this.modelViewTaskService = modelViewTaskService;
    }

    @GetMapping
    public String getAllTasksForUserProjectAndRelease
            (@PathVariable UUID projectId, @PathVariable UUID releaseId, Model model) {

           List<TaskResponseDto> taskResponseDtoList = modelViewTaskService.getTasksFromProjectAndRelease( projectId, releaseId);
           model.addAttribute("tasksList", taskResponseDtoList);

        return "taskList";
    }


}
