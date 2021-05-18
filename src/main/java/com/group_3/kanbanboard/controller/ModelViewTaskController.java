package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import com.group_3.kanbanboard.service.ProjectService;
import com.group_3.kanbanboard.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/projects/{projectId}/releases/{releaseId}/tasks/")
public class ModelViewTaskController {

    private final ProjectService projectService;
    private final ReleaseService releaseService;


    @Autowired
    public ModelViewTaskController(ProjectService projectService, ReleaseService releaseService) {
        this.projectService = projectService;
        this.releaseService = releaseService;
    }

    public void getAllTasksForProjectAndRelease(@PathVariable UUID projectId, @PathVariable UUID releaseId) {
        tas


    }
}
