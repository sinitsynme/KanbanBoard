package com.group_3.kanbanboard.rest;

import com.group_3.kanbanboard.rest.dto.TaskRequestDto;
import com.group_3.kanbanboard.rest.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Schema(description = "задача")
@RequestMapping("api/tasks")
@RestController
public class TaskController {
    @Operation(summary = "Получить список всех задач")
    @GetMapping()
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return null;
    }

    @Operation(summary = "Добавить задачу")
    @PostMapping()
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto requestDto) {
        return ResponseEntity.ok().body(new TaskResponseDto());
    }

    @Operation(summary = "Удалить задачу")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDto> deleteTask(@PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "получить задачу по id")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(new TaskResponseDto());
    }

    @Operation(summary = "обновить задачу")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable UUID id, @RequestBody TaskRequestDto requestDto) {
        return ResponseEntity.ok().body(new TaskResponseDto());
    }
}
