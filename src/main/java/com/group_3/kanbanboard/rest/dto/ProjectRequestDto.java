package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectRequestDto {
    @Schema(description = "Название проекта")
    private String title;
    @Schema(description = "id проекта")
    private Long projectId;
    @Schema(description = "Описание проекта")
    private String description;
    @Schema(description = "Создатель проекта")
    private Long leadId;
    @Schema(description = "Старт проекта")
    private Boolean startProject;

    public ProjectRequestDto(String title, Long projectId, String description, Long leadId, Boolean startProject) {
        this.title = title;
        this.projectId = projectId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
