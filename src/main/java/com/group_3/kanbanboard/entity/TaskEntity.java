package com.group_3.kanbanboard.entity;

import com.group_3.kanbanboard.enums.TaskCategory;
import com.group_3.kanbanboard.enums.TaskStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 64)
    private String title;

    @Column
    private String description;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "task_category")
    @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ProjectEntity project;

    @ManyToOne
    private ReleaseEntity release;


}
