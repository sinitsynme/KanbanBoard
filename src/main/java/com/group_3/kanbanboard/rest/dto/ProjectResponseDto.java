package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectResponseDto {
    @Schema(description = "Название проекта")
    private String title;
    @Schema(description = "Описание проекта")
    private String description;
    @Schema(description = "СОздатель проекта")
    private Long lead_id;
    @Schema(description = "Старт проекта")
    private Boolean is_started;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(String title, String description, Long lead_id, Boolean is_started) {
        this.title = title;
        this.description = description;
        this.lead_id = lead_id;
        this.is_started = is_started;
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

    public Long getLead_id() {
        return lead_id;
    }

    public void setLead_id(Long lead_id) {
        this.lead_id = lead_id;
    }

    public Boolean getIs_started() {
        return is_started;
    }

    public void setIs_started(Boolean is_started) {
        this.is_started = is_started;
    }
}
