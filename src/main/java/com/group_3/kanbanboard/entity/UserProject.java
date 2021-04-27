package com.group_3.kanbanboard.entity;


import com.group_3.kanbanboard.enums.UserRole;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_project")
public class UserProject {

    @EmbeddedId
    private UserProjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private ProjectEntity project;

    @Column (name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    public UserProject(UserEntity user, ProjectEntity project, UserRole userRole) {
        this.user = user;
        this.project = project;
        this.userRole = userRole;
        this.id = new UserProjectId(user.getId(), project.getId());
    }

    public UserProject() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProject that = (UserProject) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, project);
    }

    public UserProjectId getId() {
        return id;
    }

    public void setId(UserProjectId id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
