package com.kubrak.petproject.controller;

import com.kubrak.petproject.dto.UserForm;
import com.kubrak.petproject.dto.UserView;
import com.kubrak.petproject.entities.User;
import com.kubrak.petproject.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsersPageController {

  private final UserService service;
  private final ConversionService converter;

  @GetMapping("/users")
  public String users(Model model) {

    List<User> users = service.readAll();
    List<UserView> userViews = users.stream()
        .map(user -> converter.convert(user, UserView.class))
        .collect(Collectors.toList());
    model.addAttribute("users", userViews);

    return "users";
  }

  @GetMapping("/add-user")
  public String addUser(Model model) {

    model.addAttribute("userForm", new UserForm());

    return "add-user";
  }

  @PostMapping("/add-user")
  public String getUser(@ModelAttribute UserForm userForm) {

    service.create(converter.convert(userForm, User.class));

    return "add-user";
  }
}
