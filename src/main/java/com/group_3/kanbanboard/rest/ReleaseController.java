package com.group_3.kanbanboard.rest;

import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
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

  /**
   *
   * @param id Release id
   * @return Release with the given id
   */
  @Operation(summary = "Получение релиза по id")
  @GetMapping("/{id}")
  public ResponseEntity<ReleaseResponseDto> getReleaseById(@PathVariable Long id) {
    return ResponseEntity.ok().body(new ReleaseResponseDto());
  }

  /**
   * Create a release
   * @param releaseRequestDto Release being added
   * @param projectId id of project to where release is being added
   * @return Added release
   * @throws IOException Release with the same version already exists
   * @throws IOException Project with id doesn't exist
   */
  @Operation(summary = "Добавление релиза")
  @PostMapping()
  public ResponseEntity<ReleaseResponseDto> createRelease(Long projectId,
      @RequestBody ReleaseRequestDto releaseRequestDto) throws IOException{

    return ResponseEntity.ok(new ReleaseResponseDto());
  }

  /**
   * Update a release
   * @param id Release id
   * @param releaseRequestDto New version of release
   * @return Updated release
   * @throws IOException Id not found
   */
  @Operation(summary = "Обновление релиза")
  @PutMapping("/{id}")
  public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable Long id,
      @RequestBody ReleaseRequestDto releaseRequestDto) throws IOException {

    return ResponseEntity.ok().body(new ReleaseResponseDto());

  }

  /**
   * Delete a non-intentional created release
   * @param id Release id
   * @return Deleted release
   * @throws IOException Id not found
   */
  @Operation(summary = "Удаление релиза")
  @DeleteMapping("/{id}")
  public ResponseEntity<ReleaseResponseDto> deleteRelease(@PathVariable Long id) throws IOException{
    return ResponseEntity.ok().body(new ReleaseResponseDto());
  }

}
