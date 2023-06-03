package com.kubrak.petproject.controller;

import com.kubrak.petproject.dto.ProjectForm;
import com.kubrak.petproject.dto.ProjectView;
import com.kubrak.petproject.entities.Project;
import com.kubrak.petproject.service.ProjectService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectsRestController {

  private final ProjectService service;
  private final ConversionService converter;

  @GetMapping("/projects")
  public List<ProjectView> readAll() {

    return service.readAll()
        .stream()
        .map(project -> converter.convert(project, ProjectView.class))
        .collect(Collectors.toList());
  }

  @GetMapping("/project/{id:\\d+}")
  public ProjectView readById(@PathVariable Long id) {

    Project projectById = service.readById(id);

    return converter.convert(projectById, ProjectView.class);
  }

  @PostMapping("/projects")
  public void create(@RequestBody ProjectForm projectForm) {

    service.create(converter.convert(projectForm, Project.class));
  }

  @PutMapping("/project/{id}")
  public void update(@RequestBody ProjectForm projectForm, @PathVariable Long id) {

    Project updatedProject = converter.convert(projectForm, Project.class);
    service.update(updatedProject, id);
  }

  @DeleteMapping("/project/{id}")
  public void delete(@PathVariable Long id) {

    service.delete(id);
  }
}
