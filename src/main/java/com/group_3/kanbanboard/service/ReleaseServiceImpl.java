package com.group_3.kanbanboard.service;

import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.mappers.ReleaseMapper;
import com.group_3.kanbanboard.repository.ReleaseRepository;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReleaseServiceImpl implements ReleaseService {

  private final ReleaseRepository releaseRepository;
  //private final ProjectServiceImpl projectService;
  private final ReleaseMapper releaseMapper;

  @Autowired
  public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseMapper releaseMapper) {
    this.releaseMapper = releaseMapper;
    this.releaseRepository = releaseRepository;
    //this.projectService = projectService
  }

  /*
  !!! new constructor

  @Autowired
  public ReleaseServiceImpl(ReleaseRepository releaseRepository,
      ProjectServiceImpl projectService) {
    this.releaseRepository = releaseRepository;
    this.projectService = projectService;
  }*/


  @Transactional
  @Override
  public ReleaseResponseDto getById(UUID id) throws ReleaseNotFoundException {
    ReleaseEntity release = releaseRepository.findById(id).orElseThrow(
        () -> new ReleaseNotFoundException("Release with ID = " + id + " was not found"));
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
  public ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto)
      throws ProjectNotFoundException {
     ReleaseEntity release = releaseMapper.toEntity(releaseRequestDto);

    // Project project = projectService.getById(releaseRequestDto.getProjectId()); MAP?
    // (map dtos or inject projectRepository right away with duplicating code);

    // project.setReleases(project.getReleases().add(release));
    // releaseRepository.save(release)
    // projectService.updateProject(project)

    return releaseMapper.toResponseDto(release);
  }

  @Transactional
  @Override
  public ReleaseResponseDto updateRelease(UUID id, ReleaseRequestDto releaseRequestDto)
      throws ReleaseNotFoundException {
    ReleaseEntity releaseEntityFromDb = releaseRepository.findById(id).orElseThrow(
        () -> new ReleaseNotFoundException("Release with ID = " + id + " was not found"));
    releaseEntityFromDb.setStatus(releaseRequestDto.getStatus());
    releaseEntityFromDb.setVersion(releaseRequestDto.getVersion());
    //maybe there's smth else we need to change
    releaseRepository.save(releaseEntityFromDb);

    return releaseMapper.toResponseDto(releaseEntityFromDb);
  }

  @Transactional
  @Override
  public void deleteReleaseById(UUID id) throws ReleaseNotFoundException {
    if(!releaseRepository.existsById(id)){
      throw new ReleaseNotFoundException("Release with ID = " + id + " was not found");
    }
    releaseRepository.deleteById(id);
  }
}
