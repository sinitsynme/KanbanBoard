package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectResponseDto {
    @Schema(description = "Название проекта")
    private String title;
    @Schema(description = "Описание проекта")
    private String description;
    @Schema(description = "Создатель проекта")
    private Long leadId;
    @Schema(description = "Старт проекта")
    private Boolean startProject;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(String title, String description, Long leadId, Boolean startProject) {
        this.title = title;
        this.description = description;
        this.leadId = leadId;
        this.startProject = startProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Boolean isStartProject() {
        return startProject;
    }

    public void setStartProject(Boolean startProject) {
        this.startProject = startProject;
    }
}
