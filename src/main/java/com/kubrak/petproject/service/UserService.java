package com.kubrak.petproject.service;

import com.kubrak.petproject.entities.KeySkill;
import com.kubrak.petproject.entities.User;
import com.kubrak.petproject.exceptions.EntityNotFoundException;
import com.kubrak.petproject.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private static final String USER_NOT_EXIST_EXCEPTION_MESSAGE = "User with this id doesn't exists";

  private final UserRepository repository;
  private final KeySkillService keySkillService;

  @Transactional
  public void create(User user) {

    Long userId = user.getId();
    if (userId != null && !repository.existsById(userId)) {

      return;
    }
    Set<KeySkill> keySkills = compareSkillsWithDb(user);
    user.setKeySkills(keySkills);

    repository.save(user);
  }

  public List<User> readAll() {

    return repository.findAll();
  }

  public User readById(Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(USER_NOT_EXIST_EXCEPTION_MESSAGE));
  }

  @Transactional
  public void update(User user, Long id) {

    User userFromDb = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(USER_NOT_EXIST_EXCEPTION_MESSAGE));
    Set<KeySkill> keySkills = compareSkillsWithDb(user);
    user.setKeySkills(keySkills);
    user.setId(id);
    if (user.getName() == null) {
      user.setName(userFromDb.getName());
    }
    if (user.getEmail() == null) {
      user.setEmail(userFromDb.getEmail());
    }
    if (user.getKeySkills().isEmpty()) {
      user.setKeySkills(userFromDb.getKeySkills());
    }

    repository.save(user);
  }

  @Transactional
  public void delete(Long id) {

    repository.deleteById(id);
  }

  Set<KeySkill> compareSkillsWithDb(User user) {

    return user.getKeySkills()
        .stream()
        .map(KeySkill::getName)
        .map(keySkillService::getOrCreate)
        .collect(Collectors.toSet());
  }
}
