package com.kubrak.petproject.controller;

import com.kubrak.petproject.dto.ProjectForm;
import com.kubrak.petproject.dto.ProjectView;
import com.kubrak.petproject.entities.Project;
import com.kubrak.petproject.service.ProjectService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProjectsPageController {

  private final ProjectService service;
  private final ConversionService converter;

  @GetMapping("/project/{id:\\d+}")
  public String getProjectPage(@PathVariable Long id, Model model) {

    Project projectById = service.readById(id);
    ProjectView projectView = converter.convert(projectById, ProjectView.class);
    model.addAttribute("project", projectView);

    return "project";
  }

  @GetMapping("/projects")
  public String getProjectsPage(Model model) {

    List<Project> projects = service.readAll();
    List<ProjectView> projectViews = projects.stream()
        .map(project -> converter.convert(project, ProjectView.class))
        .collect(Collectors.toList());
    model.addAttribute("projects", projectViews);

    return "projects";
  }

  @GetMapping("/create-project")
  public String createProjectPage(Model model) {

    model.addAttribute("projectForm", new ProjectForm());

    return "create-project";
  }

  @PostMapping("/create-project")
  public String getProjectPage(@ModelAttribute ProjectForm projectForm) {

    service.create(converter.convert(projectForm, Project.class));

    return "create-project";
  }
}
