package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.exception.TaskNotFoundException;
import com.group_3.kanbanboard.mappers.TaskMapper;
import com.group_3.kanbanboard.repository.TaskRepository;
import com.group_3.kanbanboard.rest.dto.TaskRequestDto;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import com.group_3.kanbanboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    @Override
    public TaskResponseDto getById(UUID id) throws TaskNotFoundException {
        TaskEntity task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task with ID = " + id + " not found"));

        return taskMapper.toResponseDto(task);
    }

    @Transactional
    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        TaskEntity task = taskMapper.toEntity(taskRequestDto);
        taskRepository.save(task);
        return taskMapper.toResponseDto(task);

    }

    @Transactional
    @Override
    public TaskResponseDto updateTask(UUID id, TaskRequestDto taskRequestDto) throws TaskNotFoundException {
        TaskEntity taskFromDb = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task with ID = " + id + " not found"));

        TaskEntity taskFromDto = taskMapper.toEntity(taskRequestDto);
        taskFromDto.setId(taskFromDb.getId());
        taskRepository.save(taskFromDto);

        return taskMapper.toResponseDto(taskFromDb);

    }

    @Transactional
    @Override
    public void deleteTask(UUID id) throws TaskNotFoundException {
        if (!taskRepository.existsById(id)) throw new TaskNotFoundException("Task with ID = " + id + " not found");

        taskRepository.deleteById(id);
    }
}
