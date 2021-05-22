package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.entity.UserProjectEntity;
import com.group_3.kanbanboard.entity.UserProjectId;
import com.group_3.kanbanboard.exception.TaskNotFoundException;
import com.group_3.kanbanboard.mappers.UserProjectMapper;
import com.group_3.kanbanboard.repository.UserProjectRepository;
import com.group_3.kanbanboard.rest.dto.UserProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.UserProjectResponseDto;
import com.group_3.kanbanboard.service.EntityService;
import com.group_3.kanbanboard.service.TaskService;
import com.group_3.kanbanboard.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserProjectServiceImpl implements UserProjectService {

    private final UserProjectRepository userProjectRepository;
    private final EntityService entityService;
    private final UserProjectMapper userProjectMapper;


    @Autowired
    public UserProjectServiceImpl(UserProjectRepository userProjectRepository, EntityService entityService, UserProjectMapper userProjectMapper) {
        this.userProjectRepository = userProjectRepository;
        this.entityService = entityService;
        this.userProjectMapper = userProjectMapper;
    }
    @Transactional
    @Override
    public UserProjectResponseDto getById(UserProjectId id) {
        UserProjectEntity userProject = userProjectRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(String.format("userProject not found")));

        return userProjectMapper.toResponseDto(userProject);
    }

    @Transactional
    @Override
    public UserProjectResponseDto addUserProject(UserProjectRequestDto userProjectRequestDto) {
        UserProjectEntity userProject = userProjectMapper.toEntity(userProjectRequestDto);
        userProjectRepository.save(userProject);
        return userProjectMapper.toResponseDto(userProject);
    }

    @Transactional
    @Override
    public UserProjectResponseDto updateUserProject(UserProjectId id, UserProjectRequestDto userProjectRequestDto) {
        UserProjectEntity userProjectFromDb = userProjectRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(String.format("Task with ID = %s not found", id)));

        UserProjectEntity userProjectFromDto = userProjectMapper.toEntity(userProjectRequestDto);
        userProjectFromDto.setId(userProjectFromDb.getId());
        userProjectRepository.save(userProjectFromDto);

        return userProjectMapper.toResponseDto(userProjectFromDto);
    }
    @Transactional
    @Override
    public void deleteUserProject(UserProjectId id) {
        if (!userProjectRepository.existsById(id)) throw new TaskNotFoundException(String.format("Task with ID = %s not found", id));

        userProjectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<UserProjectResponseDto> getUserProjectsFromUser(UUID userId) {
        UserEntity user = entityService.getUserEntity(userId);
        List<UserProjectEntity> usersAndProjects = userProjectRepository.findByUser(user);

       return usersAndProjects.stream().map(userProjectMapper::toResponseDto).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public List<UserProjectResponseDto> getUserProjectsFromProject(UUID projectId) {
        ProjectEntity project = entityService.getProjectEntity(projectId);
        List<UserProjectEntity> projectsAndUsers = userProjectRepository.findByProject(project);

        return projectsAndUsers.stream().map(userProjectMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public UserProjectResponseDto getUserProjectByUserAndProject(UUID userId, UUID projectId) {
        return null;
    }
}
