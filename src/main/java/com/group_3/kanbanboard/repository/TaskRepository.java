package com.group_3.kanbanboard.repository;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.entity.TaskEntity;
import com.group_3.kanbanboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    List<TaskEntity> findByPerformerAndProjectAndRelease(UserEntity performer, ProjectEntity project, ReleaseEntity release);

}
