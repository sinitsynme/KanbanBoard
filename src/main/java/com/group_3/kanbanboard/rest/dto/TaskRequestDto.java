package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Задача")
public class TaskRequestDto {
//    @Schema(description ="id задачи")
//    private UUID id;
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

    public TaskRequestDto() {
    }

    public TaskRequestDto(String title, String category,
                          String description, String status, int releaseId) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.status = status;
        this.releaseId = releaseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }
}
