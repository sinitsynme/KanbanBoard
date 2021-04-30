package com.group_3.kanbanboard.repository;

import com.group_3.kanbanboard.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
