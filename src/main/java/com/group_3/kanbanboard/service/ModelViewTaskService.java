package com.group_3.kanbanboard.service;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface ModelViewTaskService {

   List<TaskResponseDto> getTasksFromUserProjectAndRelease(String userName, UUID projectId, UUID releaseId);

   UserResponseDto getUserByUserName(String userName);

}
