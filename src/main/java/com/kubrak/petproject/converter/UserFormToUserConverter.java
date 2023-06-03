package com.kubrak.petproject.converter;

import com.kubrak.petproject.dto.UserForm;
import com.kubrak.petproject.entities.KeySkill;
import com.kubrak.petproject.entities.User;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserFormToUserConverter implements Converter<UserForm, User> {

  private static final String COMMA_WITH_SPACE = ", ";

  @Override
  public User convert(UserForm form) {

    Set<KeySkill> keySkills = new HashSet<>();
    if (form.getKeySkills() != null) {
      parseSkillsFromForm(form.getKeySkills())
          .forEach(skillName -> keySkills.add(KeySkill.builder()
              .name(skillName)
              .build()));
    }

    return User.builder()
        .id(form.getId())
        .name(form.getName())
        .email(form.getEmail())
        .keySkills(keySkills)
        .build();
  }

  List<String> parseSkillsFromForm(String formKeySkills) {

    return Arrays.stream(formKeySkills.split(COMMA_WITH_SPACE))
        .collect(Collectors.toList());
  }
}
