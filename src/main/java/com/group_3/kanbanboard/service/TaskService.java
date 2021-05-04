package com.group_3.kanbanboard.service;

import com.group_3.kanbanboard.rest.dto.TaskRequestDto;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponseDto getById(UUID id);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(UUID id, TaskRequestDto taskRequestDto);

    void deleteTask(UUID id);
}
