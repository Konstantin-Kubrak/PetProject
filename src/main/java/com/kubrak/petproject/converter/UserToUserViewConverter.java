package com.kubrak.petproject.converter;

import com.kubrak.petproject.dto.UserView;
import com.kubrak.petproject.entities.KeySkill;
import com.kubrak.petproject.entities.Project;
import com.kubrak.petproject.entities.ProjectRole;
import com.kubrak.petproject.entities.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserViewConverter implements Converter<User, UserView> {

  private static final String COMMA_WITH_SPACE = ", ";

  @Override
  public UserView convert(User user) {

    Set<KeySkill> keySkills = user.getKeySkills();
    List<ProjectRole> projectRoles = user.getProjectRoles();

    return UserView.builder()
        .id(user.getId())
        .name(user.getName())
        .keySkills(castSkillsToString(keySkills))
        .projects(castRolesToString(projectRoles))
        .email(user.getEmail())
        .build();
  }

  String castRolesToString(List<ProjectRole> roles) {

    return roles.stream()
        .map(ProjectRole::getProject)
        .map(Project::getName)
        .collect(Collectors.joining(COMMA_WITH_SPACE));
  }

  String castSkillsToString(Set<KeySkill> keySkills) {

    return keySkills.stream()
        .map(KeySkill::getName)
        .collect(Collectors.joining(COMMA_WITH_SPACE));
  }
}
