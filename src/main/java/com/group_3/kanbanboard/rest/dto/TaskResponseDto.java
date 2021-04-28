package com.group_3.kanbanboard.rest.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class  TaskResponseDto {

    @Schema(description = "Название задачи")
    private String title;
    @Schema(description = "Категория задачи")
    private String category;
    @Schema(description = "Описание задачи")
    private String description;
    @Schema(description = "Статус задачи")
    private String status;
    @Schema(description = "Версия релиза")
    private int releaseId;

    public TaskResponseDto(String title, String category, String description, String status, int releaseId) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.status = status;
        this.releaseId = releaseId;
    }

    public TaskResponseDto() {
    }
}
