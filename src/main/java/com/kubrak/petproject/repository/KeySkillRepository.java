package com.kubrak.petproject.repository;

import com.kubrak.petproject.entities.KeySkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeySkillRepository extends JpaRepository<KeySkill, Long> {

  KeySkill findByName(String name);
}
