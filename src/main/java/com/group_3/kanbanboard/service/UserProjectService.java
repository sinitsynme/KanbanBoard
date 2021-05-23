package com.group_3.kanbanboard.service;

import com.group_3.kanbanboard.entity.UserProjectId;
import com.group_3.kanbanboard.rest.dto.UserProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.UserProjectResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserProjectService {

    UserProjectResponseDto getById(UserProjectId id);

    UserProjectResponseDto addUserProject(UserProjectRequestDto userProjectRequestDto);

    UserProjectResponseDto updateUserProject(UserProjectId id, UserProjectRequestDto userProjectRequestDto);

    void deleteUserProject(UserProjectId id);

    boolean isUserLeadInProject(UUID userId, UUID projectId);
}
