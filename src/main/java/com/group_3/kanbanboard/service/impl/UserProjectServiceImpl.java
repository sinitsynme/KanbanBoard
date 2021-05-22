package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.UserProjectEntity;
import com.group_3.kanbanboard.entity.UserProjectId;
import com.group_3.kanbanboard.exception.TaskNotFoundException;
import com.group_3.kanbanboard.mappers.UserProjectMapper;
import com.group_3.kanbanboard.repository.UserProjectRepository;
import com.group_3.kanbanboard.rest.dto.UserProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.UserProjectResponseDto;
import com.group_3.kanbanboard.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserProjectServiceImpl implements UserProjectService {

    private final UserProjectRepository userProjectRepository;
    private final UserProjectMapper userProjectMapper;


    @Autowired
    public UserProjectServiceImpl(UserProjectRepository userProjectRepository, UserProjectMapper userProjectMapper) {
        this.userProjectRepository = userProjectRepository;
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
}
