package com.kubrak.petproject.service;

import com.kubrak.petproject.entities.KeySkill;
import com.kubrak.petproject.repository.KeySkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeySkillService {

  private final KeySkillRepository repository;

  @Transactional
  public KeySkill getOrCreate(String skillName) {

    KeySkill keySkill = repository.findByName(skillName);
    if (keySkill == null) {
      keySkill = KeySkill.builder()
          .name(skillName)
          .build();

      repository.save(keySkill);
    }

    return keySkill;
  }
}
