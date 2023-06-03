package com.kubrak.petproject.exceptions;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String message) {

    super(message);
  }
}
