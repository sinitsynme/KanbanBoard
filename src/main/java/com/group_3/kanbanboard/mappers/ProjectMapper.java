package com.group_3.kanbanboard.mappers;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.enums.TaskStatus;
import com.group_3.kanbanboard.rest.dto.ProjectRequestDto;
import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(uses = {UserMapper.class, TaskMapper.class, ReleaseMapper.class}, componentModel = "spring")
public interface ProjectMapper {

    public ProjectRequestDto toRequestDto(ProjectEntity entity);
    @InheritInverseConfiguration
    public ProjectEntity toEntity(ProjectRequestDto requestDto);


    public ProjectResponseDto toResponseDto(ProjectEntity entity);
    @InheritInverseConfiguration
    public ProjectEntity toEntity(ProjectResponseDto responseDto);
}
