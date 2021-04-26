package com.group_3.kanbanboard.mappers;

import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProjectMapper.class, TaskMapper.class}, componentModel = "spring")
public interface ReleaseMapper {


    public ReleaseRequestDto toRequestDto(ReleaseEntity entity);

    @InheritInverseConfiguration
    public ReleaseEntity toReleaseEntity(ReleaseRequestDto requestDto);



    public ReleaseResponseDto toResponseDto(ReleaseEntity entity);

    @InheritInverseConfiguration
    public ReleaseEntity toReleaseEntity(ReleaseResponseDto responseDto);
}
