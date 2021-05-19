package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.exception.UserNotFoundException;
import com.group_3.kanbanboard.mappers.TaskMapper;
import com.group_3.kanbanboard.repository.ProjectRepository;
import com.group_3.kanbanboard.repository.ReleaseRepository;
import com.group_3.kanbanboard.repository.TaskRepository;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.service.ModelViewTaskService;
import com.group_3.kanbanboard.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ModelViewTaskServiceImpl implements ModelViewTaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ReleaseRepository releaseRepository;
    private final PrincipalServiceImpl principalService;
    private final TaskMapper taskMapper;


    @Autowired
    public ModelViewTaskServiceImpl(TaskRepository taskRepository, PrincipalServiceImpl principalService,
                                    ProjectRepository projectRepository, ReleaseRepository releaseRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.principalService = principalService;
        this.projectRepository = projectRepository;
        this.releaseRepository = releaseRepository;
        this.taskMapper = taskMapper;
    }


    @Override
    public List<TaskResponseDto> getTasksFromProjectAndRelease(UUID projectId, UUID releaseId) {
        UserEntity user = principalService.getPrincipalEntity();

        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(String.format("Project with id = %s not found", projectId)));

        ReleaseEntity release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException(String.format("Release with id = %s not found", releaseId)));

        List<TaskResponseDto> taskResponseDtos = taskRepository.findByPerformerAndProjectAndRelease(user, project, release).stream()
                .map(taskMapper::toResponseDto)
                .collect(Collectors.toList());

        return taskResponseDtos;

    }


}
