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

    List<UserProjectResponseDto> getUserProjectsFromUser(UUID userId);

    List<UserProjectResponseDto>  getUserProjectsFromProject(UUID projectId);

    UserProjectResponseDto getUserProjectByUserAndProject(UUID userId, UUID projectId);

}
