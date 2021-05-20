package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.exception.TaskNotFoundException;
import com.group_3.kanbanboard.exception.UserNotFoundException;
import com.group_3.kanbanboard.mappers.ReleaseMapper;
import com.group_3.kanbanboard.mappers.TaskMapper;
import com.group_3.kanbanboard.mappers.UserMapper;
import com.group_3.kanbanboard.repository.ProjectRepository;
import com.group_3.kanbanboard.repository.ReleaseRepository;
import com.group_3.kanbanboard.repository.TaskRepository;
import com.group_3.kanbanboard.repository.UserRepository;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import com.group_3.kanbanboard.service.ModelViewTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ModelViewTaskServiceImpl implements ModelViewTaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ReleaseRepository releaseRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final ReleaseMapper releaseMapper;


    @Autowired
    public ModelViewTaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository,
                                    ReleaseRepository releaseRepository, UserRepository userRepository, TaskMapper taskMapper, UserMapper userMapper, ReleaseMapper releaseMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.releaseRepository = releaseRepository;
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
        this.releaseMapper = releaseMapper;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> getTasksFromUserProjectAndRelease(String userName, UUID projectId, UUID releaseId) {

        UserEntity user = getUserEntity(userName);
        ProjectEntity project = getProjectEntity(projectId);
        ReleaseEntity release = getReleaseEntity(releaseId);

        List<TaskResponseDto> taskResponseDtos = taskRepository.findByPerformerAndProjectAndRelease(user, project, release).stream()
                .map(taskMapper::toResponseDto)
                .collect(Collectors.toList());

        return taskResponseDtos;
    }

    @Transactional
    @Override
    public TaskResponseDto getTaskByIdFromUserProjectAndRelease(String userName, UUID projectId, UUID releaseId, UUID taskId) {

        TaskEntity taskEntity = taskRepository.findByPerformerAndProjectAndReleaseAndId(
                getUserEntity(userName), getProjectEntity(projectId), getReleaseEntity(releaseId), taskId)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("Task with id = %s in release with id = %s and project with id = %s, in current login username = %s, not found",
                                taskId, releaseId, projectId, userName)));
        return taskMapper.toResponseDto(taskEntity);
    }

    @Transactional
    @Override
    public UserResponseDto getUserByUserName(String userName){
        return  userMapper.toResponseDto(getUserEntity(userName));
    }

    @Transactional
    @Override
    public ReleaseResponseDto getReleaseById(UUID id) {
        return releaseMapper.toResponseDto(getReleaseEntity(id));
    }

    private ReleaseEntity getReleaseEntity(UUID releaseId) {
        return releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException(String.format("Release with id = %s not found", releaseId)));
    }

    private ProjectEntity getProjectEntity(UUID projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(String.format("Project with id = %s not found", projectId)));
    }


    private UserEntity getUserEntity(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with username = %s not found", userName)));
    }
}
