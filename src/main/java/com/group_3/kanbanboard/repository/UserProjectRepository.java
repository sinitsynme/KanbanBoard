package com.group_3.kanbanboard.repository;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.entity.UserProjectEntity;
import com.group_3.kanbanboard.entity.UserProjectId;
import com.group_3.kanbanboard.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserProjectRepository extends JpaRepository<UserProjectEntity, UserProjectId> {

    List<UserProjectEntity> findByUser(UserEntity user);
    List<UserProjectEntity> findByProject(ProjectEntity project);

    Optional<UserProjectEntity> findByUserAndProject(UserEntity user, ProjectEntity project);
}
