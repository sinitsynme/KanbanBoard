package com.group_3.kanbanboard.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пользователь")
public class UserRequestDto {
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "id пользователя")
    private Long user_id;
    @Schema(description = "E-mail")
    private String mail;
    @Schema(description = "Роль")
    private String role;

    public UserRequestDto(String name, Long user_id, String mail, String role) {
        this.name = name;
        this.user_id = user_id;
        this.mail = mail;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
