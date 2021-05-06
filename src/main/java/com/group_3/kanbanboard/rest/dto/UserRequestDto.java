package com.group_3.kanbanboard.rest.dto;

import com.group_3.kanbanboard.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;

@Schema(description = "Пользователь")
public class UserRequestDto {
    @Schema(description = "Имя пользователя")
    private String firstName;
    @Schema(description = "Фамилия пользователя")
    private String secondName;
    @Schema(description = "Password")
    private String password;
    @Schema(description = "Username")
    private String username;
    @Schema(description = "E-mail")
    private String mail;
    @Schema(description = "Роли")
    private Set<UserRole> roles;

    public UserRequestDto(String firstName, String secondName, String password, String username, String mail, Set<UserRole> roles) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
