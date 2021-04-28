package com.group_3.kanbanboard.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "leadId")
    private Long leadId;
    @Column(name = "startProject")
    private Boolean startProject;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserProjectEntity> users = new ArrayList<>();

    @OneToMany //......
    private ReleaseEntity release;

    public ProjectEntity() {
    }

    public ProjectEntity(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isStartProject() {
        return startProject;
    }

    public void setStartProject(boolean startProject) {
        this.startProject = startProject;
    }

    public List<UserProjectEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserProjectEntity> users) {
        this.users = users;
    }

    public ReleaseEntity getRelease() {
        return release;
    }

    public void setRelease(ReleaseEntity release) {
        this.release = release;
    }

}
