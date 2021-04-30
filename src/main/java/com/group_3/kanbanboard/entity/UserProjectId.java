package com.group_3.kanbanboard.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserProjectId implements Serializable {

    @Column (name = "user_id")
    private UUID userId;

    @Column (name = "project_id")
    private UUID projectId;

    public UserProjectId() {
    }

    public UserProjectId(UUID userId, UUID projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProjectId that = (UserProjectId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, projectId);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }
}
