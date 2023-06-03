package com.kubrak.petproject.service;

import com.kubrak.petproject.entities.Technology;
import com.kubrak.petproject.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TechnologyService {

  private final TechnologyRepository repository;

  @Transactional
  public Technology getOrCreate(String technologyName) {

    Technology technology = repository.findByName(technologyName);
    if (technology == null) {
      technology = Technology.builder()
          .name(technologyName)
          .build();

      repository.save(technology);
    }

    return technology;
  }
}
