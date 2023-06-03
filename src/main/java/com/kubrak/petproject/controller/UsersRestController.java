package com.kubrak.petproject.controller;

import com.kubrak.petproject.dto.UserForm;
import com.kubrak.petproject.dto.UserView;
import com.kubrak.petproject.entities.User;
import com.kubrak.petproject.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersRestController {

  private final UserService service;
  private final ConversionService converter;

  @GetMapping("/users")
  public List<UserView> readAll() {

    return service.readAll()
        .stream()
        .map(user -> converter.convert(user, UserView.class))
        .collect(Collectors.toList());
  }

  @GetMapping("/users/{id}")
  public UserView readById(@PathVariable Long id) {

    User userById = service.readById(id);

    return converter.convert(userById, UserView.class);
  }

  @PostMapping("/users")
  public void create(@RequestBody UserForm userForm) {

    service.create(converter.convert(userForm, User.class));
  }

  @PutMapping("/users/{id}")
  public void update(@RequestBody UserForm userForm, @PathVariable Long id) {

    User updatedUser = converter.convert(userForm, User.class);
    service.update(updatedUser, id);
  }

  @DeleteMapping("/users/{id}")
  public void delete(@PathVariable Long id) {

    service.delete(id);
  }
}
