package com.group_3.kanbanboard.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserSignUpRequest {

    @NotBlank
    @Size(min = 3, max = 15, message = "Invalid first name")
    private String firstName;

    @NotBlank
    @Size(min = 5, max = 25, message = "Invalid last name")
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 25, message = "Invalid last name")
    private String userName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 50, min = 8, message = "Invalid password")
    private String password;

    @NotBlank
    private String confirmPassword;

    public UserSignUpRequest(String firstName,
                             String lastName,
                             String userName,
                             String email,
                             String password,
                             String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserSignUpRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignUpRequest that = (UserSignUpRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, email, password, confirmPassword);
    }
}
