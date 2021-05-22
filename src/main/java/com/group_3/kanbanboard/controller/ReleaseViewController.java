package com.group_3.kanbanboard.controller;

import com.group_3.kanbanboard.rest.dto.ProjectResponseDto;
import com.group_3.kanbanboard.rest.dto.ReleaseRequestDto;
import com.group_3.kanbanboard.rest.dto.ReleaseResponseDto;
import com.group_3.kanbanboard.service.PrincipalService;
import com.group_3.kanbanboard.service.ProjectService;
import com.group_3.kanbanboard.service.ReleaseService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/projects/{projectId}/releases")
public class ReleaseViewController {

  private final PrincipalService principalService;
  private final ReleaseService releaseService;
  private final ProjectService projectService;

  @Autowired
  public ReleaseViewController(PrincipalService principalService,
      ReleaseService releaseService, ProjectService projectService) {
    this.principalService = principalService;
    this.releaseService = releaseService;
    this.projectService = projectService;
  }

  @GetMapping
  public ModelAndView getProjectReleases(@PathVariable UUID projectId) {
    ProjectResponseDto projectResponseDto = projectService.getById(projectId);

    ModelAndView modelAndView = new ModelAndView("releases/releaseList");
    modelAndView.addObject("projectDTO", projectResponseDto);

    return modelAndView;
  }

  @GetMapping("/new")
  public ModelAndView getAddReleasePage(){
    ModelAndView modelAndView = new ModelAndView();
    return modelAndView;
  }

  @PostMapping
  public ModelAndView addReleaseToProject(@RequestBody ReleaseRequestDto releaseRequestDto){
    ModelAndView modelAndView = new ModelAndView();

    return modelAndView;
  }




}
