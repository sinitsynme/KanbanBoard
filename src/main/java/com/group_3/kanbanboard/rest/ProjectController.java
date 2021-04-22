package com.group_3.kanbanboard.rest;

import com.group_3.kanbanboard.rest.dto.ProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Проекты")
@RequestMapping("/api/projects")
@RestController
public class ProjectController {

    @Operation(summary = "Добавить проект")
    @PostMapping()
    public ResponseEntity<ProjectResponseDto> addProject(@RequestBody ProjectRequestDto requestDto)  {
        return ResponseEntity.ok().body(new ProjectResponseDto(requestDto.getTitle(), requestDto.getDescription(), requestDto.getLead_id(), requestDto.getIs_started()));
    }

    @Operation(summary = "Получить список всех проектов")
    @PostMapping()
    public ResponseEntity<ProjectResponseDto> getProjects()  {
        return null;
    }

    @Operation(summary = "Получение проекта по id")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ProjectResponseDto());
    }

    @Operation(summary = "Получение создателя проекта")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getLeadId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ProjectResponseDto());
    }

    @Operation(summary = "Удаление проекта")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id)  {

        return ResponseEntity.ok().build();
    }

}
