package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.exception.TaskNotFoundException;
import com.group_3.kanbanboard.mappers.TaskMapper;
import com.group_3.kanbanboard.repository.ReleaseRepository;
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
   private final ReleaseRepository releaseRepository;
   private final TaskMapper taskMapper;

   @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ReleaseRepository releaseRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.releaseRepository = releaseRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    @Override
    public TaskResponseDto getById(UUID id) throws TaskNotFoundException{
        TaskEntity task =  taskRepository.findById(id).orElseThrow(
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
    public TaskResponseDto addTask(UUID releaseId, TaskRequestDto taskRequestDto) throws ReleaseNotFoundException {
        ReleaseEntity release = releaseRepository.findById(releaseId).orElseThrow(
                () -> new ReleaseNotFoundException("Release with ID = " + releaseId + " not found"));

        TaskEntity task = taskMapper.toEntity(taskRequestDto);

        List<TaskEntity> tasksFromDb = release.getTasks();

        tasksFromDb.add(task);
        release.setTasks(tasksFromDb);
        releaseRepository.save(release);

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
    public void deleteTask(UUID id) throws TaskNotFoundException{
        if(!taskRepository.existsById(id)) throw new TaskNotFoundException("Task with ID = " + id + " not found");

        taskRepository.deleteById(id);
    }
}
