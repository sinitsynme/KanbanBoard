package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskRequestDto {
    @Schema(description ="id задачи")
    private Long id;
    @Schema(description = "название задачи")
    private String title;
    @Schema(description = "категория задачи")
    private String category;
    @Schema(description = "описание задачи")
    private String description;
    @Schema(description = "статус задачи")
    private String status;
    @Schema(description = "версия релиза")
    private int releaseId;

}
