package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.ProjectEntity;
import com.group_3.kanbanboard.entity.ReleaseEntity;
import com.group_3.kanbanboard.enums.ReleaseStatus;
import com.group_3.kanbanboard.exception.ReleaseNotFoundException;
import com.group_3.kanbanboard.mappers.ReleaseMapper;
import com.group_3.kanbanboard.mappers.ReleaseMapperImpl;
import com.group_3.kanbanboard.repository.ReleaseRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReleaseServiceImplTest {

  private static final UUID releaseId1 = UUID.randomUUID();
  private static final UUID releaseId2 = UUID.randomUUID();
  private static final UUID releaseId3 = UUID.randomUUID();


  private static final String releaseName1 = "RELEASE_NAME_1";
  private static final String releaseName2 = "RELEASE_NAME_2";
  private static final String releaseName3 = "RELEASE_NAME_3";
  private static final ReleaseStatus releaseStatus2and3 = ReleaseStatus.BACKLOG;
  private static final ReleaseStatus releaseStatus1 = ReleaseStatus.IN_PROGRESS;
  private static final String releaseVersion1 = "V1.0";
  private static final String releaseVersion2 = "V2.0";
  private static final String releaseVersion3 = "V3.0";
  private static final Date startDate = new Date(System.currentTimeMillis());
  private static final Date endDate = new Date(System.currentTimeMillis() + 100000000);
  private static final ProjectEntity project = new ProjectEntity();

  @InjectMocks
  private ReleaseServiceImpl releaseService;

  @Mock
  private ReleaseRepository releaseRepository;

  @Mock
  private ReleaseMapper releaseMapper;

  private static ReleaseEntity release1;
  private static ReleaseEntity release2;
  private static ReleaseEntity release3;

  @Before
  public void setUp() {
    release1 = new ReleaseEntity(releaseVersion1, startDate, endDate, project, releaseStatus1);
    release2 = new ReleaseEntity(releaseVersion2, startDate, endDate, project, releaseStatus2and3);
    release3 = new ReleaseEntity(releaseVersion3, startDate, endDate, project, releaseStatus2and3);

  }

//  @After
//  void tearDown() {
//  }

  @Test
  public void getById_EXISTING_RELEASES() {
    when(releaseRepository.findById(releaseId1)).thenReturn(Optional.of(release1));
    when(releaseRepository.findById(releaseId2)).thenReturn(Optional.of(release2));
    when(releaseRepository.findById(releaseId3)).thenReturn(Optional.of(release3));

    when(releaseMapper.toResponseDto(Mockito.any(ReleaseEntity.class))).thenAnswer(
        invocation -> new ReleaseMapperImpl().toResponseDto(invocation.getArgument(0)));



    Assertions.assertAll(
        () -> assertEquals(releaseService.getById(releaseId1).getVersion(), releaseMapper.toResponseDto(release1).getVersion()),
        () -> assertEquals(releaseService.getById(releaseId2).getVersion(), releaseMapper.toResponseDto(release2).getVersion()),
        () -> assertEquals(releaseService.getById(releaseId3).getVersion(), releaseMapper.toResponseDto(release3).getVersion())
    );

  }

  @Test
  public void getById_UNEXISTING_ID(){
    UUID unexistingId = UUID.randomUUID();
    when(releaseRepository.findById(unexistingId)).thenReturn(Optional.empty());

    assertThrows(ReleaseNotFoundException.class, () -> releaseService.getById(unexistingId));

  }


  @Test
  public void getAllReleases() {
//    when(releaseRepository.findAll()).thenReturn(Arrays.asList(release1, release2));

  }

//  @Test
//  public void addRelease() {
//  }
//
//  @Test
//  public void updateRelease() {
//  }
//
//  @Test
//  public void deleteReleaseById() {
//  }
}