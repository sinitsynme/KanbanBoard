package com.group_3.kanbanboard.rest;

import com.group_3.kanbanboard.exception.ProjectNotFoundException;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import com.group_3.kanbanboard.service.ReleaseService;
import com.group_3.kanbanboard.service.ReleaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Управление релизами")
@RestController
@RequestMapping("api/releases")
public class ReleaseController {

  private final ReleaseServiceImpl releaseService;

  @Autowired
  public ReleaseController(ReleaseServiceImpl releaseService) {
    this.releaseService = releaseService;
  }

  /**
   * Get a release by its id
   *
   * @param id Release id
   * @return Release with the given id
   * @throws ReleaseNotFoundException throw if id not found
   */
  @Operation(summary = "Получение релиза по id")
  @GetMapping("/{id}")
  public ResponseEntity<ReleaseResponseDto> getReleaseById(@PathVariable UUID id)
      throws ReleaseNotFoundException {
    return ResponseEntity.ok().body(releaseService.getById(id));
  }

  /**
   * Get all releases
   * @return List of all releases
   */
  @Operation(summary = "Получить все релизы")
  @GetMapping
  public ResponseEntity<List<ReleaseResponseDto>> getAllReleases(){
    return ResponseEntity.ok().body(releaseService.getAllReleases());
  }

  /**
   * Create a release
   *
   * @param releaseRequestDto Release being added
   * @return Added release
   * @throws ProjectNotFoundException throw if release with the same version already exists
   * @throws ReleaseNotFoundException throw if project with id doesn't exist
   */
  @Operation(summary = "Добавление релиза")
  @PostMapping
  public ResponseEntity<ReleaseResponseDto> createRelease(
      @RequestBody ReleaseRequestDto releaseRequestDto)
      throws ProjectNotFoundException, ReleaseNotFoundException {

    return ResponseEntity.ok(releaseService.addRelease(releaseRequestDto));
  }

  /**
   * Update a release
   *
   * @param id                Release id
   * @param releaseRequestDto New version of release
   * @return Updated release
   * @throws ReleaseNotFoundException throw if id not found
   */
  @Operation(summary = "Обновление релиза")
  @PutMapping("/{id}")
  public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable UUID id,
      @RequestBody ReleaseRequestDto releaseRequestDto) throws ReleaseNotFoundException {

    return ResponseEntity.ok().body(releaseService.updateRelease(id, releaseRequestDto));
  }

  /**
   * Delete a non-intentional created release
   *
   * @param id Release id
   * @return Deleted release
   * @throws ReleaseNotFoundException throw if not found
   */
  @Operation(summary = "Удаление релиза")
  @DeleteMapping("/{id}")
  public ResponseEntity<ReleaseResponseDto> deleteRelease(@PathVariable UUID id)
      throws ReleaseNotFoundException {
    releaseService.deleteReleaseById(id);
    return ResponseEntity.ok().build();
  }
}