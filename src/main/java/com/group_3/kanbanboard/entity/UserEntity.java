package com.group_3.kanbanboard.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table( name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column (name = "first_name")
    private String firstName;
    @Column (name = "second_name")
    private String secondName;
    @Column (name = "login")
    private String login;
    @Column (name = "password")
    private String password;
    @Column (name = "mail")
    private String mail;
    @Column (name = "role")
    private Enum role;

//    @ManyToOne //......
//    private TaskEntity tasks;

//    @OneToMany //......
//    private List<ProjectEntity> project;


    public UserEntity() {
    }

    public UserEntity(UUID id, String firstName, String secondName, String login, String password, String mail, Enum role) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
        this.role = role;
    }
}
