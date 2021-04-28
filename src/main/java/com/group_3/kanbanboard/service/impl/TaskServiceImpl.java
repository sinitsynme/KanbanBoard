package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.repository.TaskRepository;
import com.group_3.kanbanboard.rest.dto.TaskRequestDto;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository repository;

    @Override
    public TaskResponseDto getById(UUID id) {
        TaskEntity task =  repository.findById()
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return null;
    }

    @Override
    public TaskResponseDto addTask(UUID projectId, TaskRequestDto taskRequestDto) {
        return null;
    }

    @Override
    public TaskResponseDto updateTask(UUID id, TaskRequestDto taskRequestDto) {
        return null;
    }

    @Override
    public void deleteTask(UUID id) {

    }
}
