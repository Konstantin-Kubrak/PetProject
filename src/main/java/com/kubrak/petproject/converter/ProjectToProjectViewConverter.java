package com.kubrak.petproject.converter;

import com.kubrak.petproject.dto.ProjectView;
import com.kubrak.petproject.entities.Project;
import com.kubrak.petproject.entities.ProjectRole;
import com.kubrak.petproject.entities.Technology;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectToProjectViewConverter implements Converter<Project, ProjectView> {

  private static final String COMMA_WITH_SPACE = ", ";

  @Override
  public ProjectView convert(Project project) {

    Set<Technology> technologies = project.getTechnologies();
    List<ProjectRole> projectRoles = project.getProjectRoles();

    return ProjectView.builder()
        .name(project.getName())
        .startDate(project.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
        .endDate(endDateCheck(project.getEndDate()))
        .roles(projectRoles)
        .technologies(castTechnologiesToString(technologies))
        .build();
  }

  private String endDateCheck(LocalDate endDate) {

    if (endDate != null) {

      return endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    else {

      return "In progress";
    }
  }

  private String castTechnologiesToString(Set<Technology> technologies) {

    return technologies.stream()
        .map(Technology::getName)
        .collect(Collectors.joining(COMMA_WITH_SPACE));
  }
}
