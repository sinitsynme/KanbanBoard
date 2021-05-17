package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.mappers.ProjectMapper;
import com.group_3.kanbanboard.mappers.ProjectMapperImpl;
import com.group_3.kanbanboard.repository.ProjectRepository;
import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String PROJECT_TITLE = "project";
    private static final String DESCRIPTION = "description";
    private static final UUID LEAD_ID = UUID.randomUUID();
    private static final boolean START_PROJECT = false;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;


    @Test
    public void getById() {
        ProjectEntity expected = new ProjectEntity(ID, PROJECT_TITLE, DESCRIPTION);
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(expected));
        Mockito.when(projectMapper.toResponseDto(Mockito.any(ProjectEntity.class)))
                .thenAnswer(invocation -> new ProjectMapperImpl().toResponseDto(invocation.<ProjectEntity>getArgument(0)));
        ProjectResponseDto actual = projectService.getById(ID);

        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void getAllProjects() {
    }

    @Test
    public void addProject() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void deleteProjectById() {
    }
}