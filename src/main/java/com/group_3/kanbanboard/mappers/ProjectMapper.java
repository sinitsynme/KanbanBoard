package com.group_3.kanbanboard.mappers;

import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(uses = {              }, componentModel = "spring")
public interface ProjectMapper {

    public ReleaseRequestDto toRequestDto(ReleaseEntity entity);
    @InheritInverseConfiguration
    public ReleaseEntity toEntity(ReleaseRequestDto requestDto);


    public ReleaseResponseDto toResponseDto(ReleaseEntity entity);
    @InheritInverseConfiguration
    public ReleaseEntity toEntity(ReleaseResponseDto responseDto);
}
