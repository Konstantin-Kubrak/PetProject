package com.kubrak.petproject.dto;

import com.kubrak.petproject.entities.ProjectRole;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectView {

  private String name;
  private String startDate;
  private String endDate;
  List<ProjectRole> roles;
  private String technologies;
}
