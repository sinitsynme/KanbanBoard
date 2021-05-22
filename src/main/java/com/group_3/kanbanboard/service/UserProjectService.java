package com.group_3.kanbanboard.service;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.entity.UserProjectEntity;
import com.group_3.kanbanboard.entity.UserProjectId;
import com.group_3.kanbanboard.rest.dto.UserProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.UserProjectResponseDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserProjectService {

    UserProjectResponseDto getById(UserProjectId id);

    UserProjectResponseDto addUserProject(UserProjectRequestDto userProjectRequestDto);

    UserProjectResponseDto updateUserProject(UserProjectId id, UserProjectRequestDto userProjectRequestDto);

    void deleteUserProject(UserProjectId id);

    List<UserProjectResponseDto> getUserProjectsFromUser(UUID userId);

    List<UserProjectResponseDto>  getUserProjectsFromProject(UUID projectId);

    UserProjectResponseDto getUserProjectByUserAndProject(UUID userId, UUID projectId);

}
