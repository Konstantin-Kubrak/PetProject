package com.kubrak.petproject.converter;

import com.kubrak.petproject.dto.ProjectForm;
import com.kubrak.petproject.entities.Project;
import com.kubrak.petproject.entities.Technology;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectFormToProjectConverter implements Converter<ProjectForm, Project> {

  private static final String COMMA_WITH_SPACE = ", ";

  @Override
  public Project convert(ProjectForm form) {

    Set<Technology> technologies = new HashSet<>();
    if (form.getTechnologies() != null) {
      parseTechnologiesFromForm(form.getTechnologies())
          .forEach(technologyName -> technologies.add(Technology.builder()
              .name(technologyName)
              .build()));
    }

    return Project.builder()
        .name(form.getName())
        .startDate(LocalDate.parse(form.getStartDate()))
        .technologies(technologies)
        .build();
  }

  List<String> parseTechnologiesFromForm(String formTechnologies) {

    return Arrays.stream(formTechnologies.split(COMMA_WITH_SPACE))
        .collect(Collectors.toList());
  }
}
