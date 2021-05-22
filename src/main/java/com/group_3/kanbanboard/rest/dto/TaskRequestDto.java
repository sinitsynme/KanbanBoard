package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskRequestDto {
//    @Schema(description ="id задачи")
//    private UUID id;
    @Schema(description = "Название задачи")
    private String title;
    @Schema(description = "Категория задачи")
    private String taskCategory;
    @Schema(description = "Описание задачи")
    private String description;
    @Schema(description = "Статус задачи")
    private String taskStatus;
    @Schema(description = "Версия релиза")
    private int releaseVersion;

    public TaskRequestDto() {
    }

    public TaskRequestDto(String title, String category,
                          String description, String status, int releaseId) {
        this.title = title;
        this.taskCategory = category;
        this.description = description;
        this.taskStatus = status;
        this.releaseVersion = releaseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(int releaseVersion) {
        this.releaseVersion = releaseVersion;
    }
}
