package com.kubrak.petproject.service;

import com.kubrak.petproject.entities.Project;
import com.kubrak.petproject.entities.Technology;
import com.kubrak.petproject.exceptions.EntityNotFoundException;
import com.kubrak.petproject.repository.ProjectRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

  private static final String PROJECT_NOT_EXIST_EXCEPTION_MESSAGE = "Project with this id doesn't exists";

  private final ProjectRepository repository;
  private final TechnologyService technologyService;

  @Transactional
  public void create(Project project) {

    Long projectId = project.getId();
    if (projectId != null && !repository.existsById(projectId)) {

      return;
    }
    Set<Technology> technologies = compareTechsWithDb(project);
    project.setTechnologies(technologies);

    repository.save(project);
  }

  public List<Project> readAll() {

    return repository.findAll();
  }

  public Project readById(Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_EXIST_EXCEPTION_MESSAGE));
  }

  @Transactional
  public void update(Project project, Long id) {

    Project projectFromDb = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_EXIST_EXCEPTION_MESSAGE));
    Set<Technology> technologies = compareTechsWithDb(project);
    project.setTechnologies(technologies);
    project.setId(id);
    if (project.getName() == null) {
      project.setName(projectFromDb.getName());
    }
    if (project.getStartDate() == null) {
      project.setStartDate(projectFromDb.getStartDate());
    }
    if (project.getTechnologies().isEmpty()) {
      project.setTechnologies(projectFromDb.getTechnologies());
    }

    repository.save(project);
  }

  @Transactional
  public void delete(Long id) {

    if (repository.existsById(id)) {
      repository.deleteById(id);
    }
    else {
      throw new EntityNotFoundException(PROJECT_NOT_EXIST_EXCEPTION_MESSAGE);
    }
  }

  Set<Technology> compareTechsWithDb(Project project) {

    return project.getTechnologies()
        .stream()
        .map(Technology::getName)
        .map(technologyService::getOrCreate)
        .collect(Collectors.toSet());
  }
}
