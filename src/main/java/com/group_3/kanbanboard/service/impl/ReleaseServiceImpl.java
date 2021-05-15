package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.mappers.ProjectMapper;
import com.group_3.kanbanboard.mappers.ReleaseMapper;
import com.group_3.kanbanboard.repository.ReleaseRepository;
import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import com.group_3.kanbanboard.service.ReleaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReleaseServiceImpl implements ReleaseService {

  private final ReleaseRepository releaseRepository;
  private final ProjectServiceImpl projectService;
  private final ReleaseMapper releaseMapper;
  private final ProjectMapper projectMapper;

  @Autowired
  public ReleaseServiceImpl(ReleaseRepository releaseRepository,
      ProjectServiceImpl projectService, ReleaseMapper releaseMapper, ProjectMapper projectMapper) {
    this.releaseRepository = releaseRepository;
    this.projectService = projectService;
    this.releaseMapper = releaseMapper;
    this.projectMapper = projectMapper;
  }

  @Transactional
  @Override
  public ReleaseResponseDto getById(UUID id) {
    ReleaseEntity release = releaseRepository.findById(id).orElseThrow(
        () -> new ReleaseNotFoundException(String.format("Release with ID = %s was not found", id)));
    return releaseMapper.toResponseDto(release);
  }

  @Transactional
  @Override
  public List<ReleaseResponseDto> getAllReleases() {
    List<ReleaseEntity> releases = releaseRepository.findAll();
    return releases.stream().map(releaseMapper::toResponseDto).collect(Collectors.toList());
  }

  @Transactional
  @Override
  public ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto) {
     ReleaseEntity release = releaseMapper.toEntity(releaseRequestDto);

    ProjectResponseDto projectResponseDto = projectService.getById(releaseRequestDto.getProjectId());
    ProjectEntity project = projectMapper.toEntity(projectResponseDto);

     if (project.getReleases() == null) project.setReleases(new ArrayList<>());

     project.getReleases().add(release);
     release.setProject(project);
     //releaseRepository.save(release);
     projectService.updateProject(releaseRequestDto.getProjectId(), projectMapper.toRequestDto(project));

    return releaseMapper.toResponseDto(release);
  }

  @Transactional
  @Override
  public ReleaseResponseDto updateRelease(UUID id, ReleaseRequestDto releaseRequestDto) {
    ReleaseEntity releaseEntityFromDb = releaseRepository.findById(id).orElseThrow(
        () -> new ReleaseNotFoundException(String.format("Release with ID = %s was not found", id)));
    releaseEntityFromDb.setStatus(releaseRequestDto.getStatus());
    releaseEntityFromDb.setVersion(releaseRequestDto.getVersion());
    //maybe there's smth else we need to change
    releaseRepository.save(releaseEntityFromDb);

    return releaseMapper.toResponseDto(releaseEntityFromDb);
  }

  @Transactional
  @Override
  public void deleteReleaseById(UUID id) {
    if(!releaseRepository.existsById(id)){
      throw new ReleaseNotFoundException(String.format("Release with ID = %s was not found", id));
    }
    releaseRepository.deleteById(id);
  }
}
