package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.enums.TaskCategory;
import com.group_3.kanbanboard.enums.TaskStatus;
import com.group_3.kanbanboard.mappers.TaskMapper;
import com.group_3.kanbanboard.mappers.TaskMapperImpl;
import com.group_3.kanbanboard.repository.TaskRepository;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String TITLE = "test_task";
    private static final String DESCRIPTION = "description of test_task";
    private static final Date END_DATE = new Date(System.currentTimeMillis() + 86400000);
    private static final TaskCategory TASK_CATEGORY = TaskCategory.OTHER;
    private static final TaskStatus TASK_STATUS = TaskStatus.IN_PROGRESS;


    @InjectMocks
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;

    private TaskEntity expectedTask;


    @Before
    public void setUp() {
        expectedTask = new TaskEntity(TITLE, DESCRIPTION, END_DATE, TASK_CATEGORY, TASK_STATUS,
                new UserEntity(), new ProjectEntity(), new ReleaseEntity());
    }

    @After
    public void tearDown() {
        expectedTask = null;
    }


    @Test
    public void getById() {
        Mockito.when(taskRepository.findById(Mockito.any())).thenReturn(Optional.of(expectedTask));
        Mockito.when(taskMapper.toResponseDto(Mockito.any(TaskEntity.class)))
                .thenAnswer(invocation -> new TaskMapperImpl().toResponseDto(invocation.<TaskEntity>getArgument(0)));
        TaskResponseDto actualTaskDto = taskService.getById(ID);

        Assert.assertEquals(expectedTask.getDescription(), actualTaskDto.getDescription());
        Assert.assertEquals(expectedTask.getTitle(), actualTaskDto.getTitle());

    }

//    @Test
//    void getAllTasks() {
//    }
//
//    @Test
//    void addTask() {
//    }
//
//    @Test
//    void updateTask() {
//    }
//
//    @Test
//    void deleteTask() {
//    }


}