package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.repository.ProjectRepository;
import com.group_3.kanbanboard.rest.dto.ProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import com.group_3.kanbanboard.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    @Override
    public ProjectResponseDto getById(UUID id) {
        ProjectEntity project = projectRepository.findById(id).orElseThrow(
                () -> new ProjectNotFoundException("Project with ID = " + id + " was not found"));
        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setTitle(project.getTitle());
        responseDto.setDescription(project.getDescription());
        responseDto.setLeadId(project.getLeadId());
        responseDto.setStartProject(project.isStartProject());
        return responseDto;
    }

    @Transactional
    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<ProjectEntity> projects = projectRepository.findAll();
        return null;
    }

    @Transactional
    @Override
    public ProjectResponseDto addProject(UUID userId, ProjectRequestDto projectRequestDto) {
        ProjectEntity project = new ProjectEntity();
        projectRepository.save(project);

        return null;
    }

    @Transactional
    @Override
    public ProjectResponseDto updateProject(UUID id, ProjectRequestDto projectRequestDto) {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new ProjectNotFoundException("Project with ID = " + id + " was not found"));
        projectEntity.setTitle(projectRequestDto.getTitle());
        projectEntity.setDescription(projectRequestDto.getDescription());

        projectRepository.save(projectEntity);
        return null;
    }

    @Transactional
    @Override
    public void deleteProjectById(UUID id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project with ID = " + id + " was not found");
        }
        projectRepository.deleteById(id);
    }
}
