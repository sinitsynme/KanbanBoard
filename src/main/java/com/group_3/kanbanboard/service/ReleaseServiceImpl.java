package com.group_3.kanbanboard.service;

import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.repository.ReleaseRepository;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReleaseServiceImpl implements ReleaseService {

  private final ReleaseRepository releaseRepository;
  //private final ProjectServiceImpl projectService;

  @Autowired
  public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
    this.releaseRepository = releaseRepository;
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
    //map release to dto response
    return null;
  }

  @Transactional
  @Override
  public List<ReleaseResponseDto> getAllReleases() {
    List<ReleaseEntity> releases = releaseRepository.findAll();
    //map release to dto response
    return null;
  }

  @Transactional
  @Override
  public ReleaseResponseDto addRelease(UUID projectId, ReleaseRequestDto releaseRequestDto)
      throws ProjectNotFoundException {
    // map request to entity;
    // Release release = ....

    // Project project = projectService.getById(projectId); MAP
    // (map dtos or inject projectRepository right away with duplicating code);

    // project.setReleases(project.getReleases().add(release));
    // releaseRepository.save(release)
    // projectService.updateProject(project)

    return null; //map release entity to response
  }

  @Transactional
  @Override
  public ReleaseResponseDto updateRelease(UUID id, ReleaseRequestDto releaseRequestDto)
      throws ReleaseNotFoundException {
    //map request to entity;
    ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
        () -> new ReleaseNotFoundException("Release with ID = " + id + " was not found"));
    releaseEntity.setStatus(releaseRequestDto.getStatus());
    releaseEntity.setVersion(releaseRequestDto.getVersion());
    //maybe there's smth else we need to change
    releaseRepository.save(releaseEntity);

    return null; //map to response
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
