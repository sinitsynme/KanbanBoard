package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.enums.TaskCategory;
import com.group_3.kanbanboard.enums.TaskStatus;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import com.group_3.kanbanboard.service.ModelViewTaskService;
import com.group_3.kanbanboard.service.PrincipalService;
import com.group_3.kanbanboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("users/{userName}/projects/{projectId}/releases/{releaseId}/tasks/")
public class ModelViewTaskController {

    private final ModelViewTaskService modelViewTaskService;
    private final PrincipalService principalService;
    private final TaskService taskService;


    @Autowired
    public ModelViewTaskController(ModelViewTaskService modelViewTaskService,
                                   PrincipalService principalService,
                                   TaskService taskService) {
        this.modelViewTaskService = modelViewTaskService;
        this.principalService = principalService;
        this.taskService = taskService;
    }


    @GetMapping
    public String getTasksFromUserProjectAndRelease(@PathVariable String userName,
                                                    @PathVariable UUID projectId,
                                                    @PathVariable UUID releaseId,
                                                    Model model) {

        List<TaskResponseDto> taskResponseDtoList =
                modelViewTaskService.getTasksFromUserProjectAndRelease(userName, projectId, releaseId);
        model.addAttribute("tasksList", taskResponseDtoList);

        UserResponseDto userResponseDto = modelViewTaskService.getUserByUserName(userName);
        model.addAttribute("user", userResponseDto);

        ReleaseResponseDto releaseResponseDto = modelViewTaskService.getReleaseById(releaseId);
        model.addAttribute("release", releaseResponseDto);

        model.addAttribute("statuses", TaskStatus.values());

        return "taskList";
    }

    @GetMapping("/{taskId}")
    public String getDistinctTaskById(@PathVariable String userName,
                                      @PathVariable UUID projectId,
                                      @PathVariable UUID releaseId,
                                      @PathVariable UUID taskId,
                                      Model model) {
        TaskResponseDto distinctTask = modelViewTaskService
                .getTaskByIdFromUserProjectAndRelease(userName, projectId, releaseId, taskId);

        model.addAttribute("distinctTask", distinctTask);

        return "taskDetail";


    }

    @PostMapping("/{taskId}")
    public String addTask() {
        return "/asdasd";
    }
}
