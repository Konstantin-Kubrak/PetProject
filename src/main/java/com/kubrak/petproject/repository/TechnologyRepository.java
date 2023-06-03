package com.kubrak.petproject.repository;

import com.kubrak.petproject.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

  Technology findByName(String name);
}
