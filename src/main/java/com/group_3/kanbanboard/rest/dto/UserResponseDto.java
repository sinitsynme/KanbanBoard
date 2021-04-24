package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

    @Schema(description = "Пользователь")
    public class UserResponseDto {
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "E-mail")
    private String mail;
    @Schema(description = "Роль")
    private String role;

    public UserResponseDto() {
    }

    public UserResponseDto(String name, String mail, String role) {
        this.name = name;
        this.mail = mail;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
