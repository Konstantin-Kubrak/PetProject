package com.kubrak.petproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserView {

  private Long id;
  private String name;
  private String email;
  private String projects;
  private String keySkills;
}
