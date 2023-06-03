package com.kubrak.petproject.converter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import java.util.List;
import org.junit.jupiter.api.Test;

class UserFormToUserConverterTest {

  UserFormToUserConverter userFormToUserConverter = new UserFormToUserConverter();

  @Test
  void convert() {

  }

  @Test
  void parseSkillsFromForm_validString_listOfStringReturned() {

    // GIVEN
    String validString = "SQL, Git, JAVA, Spring";
    List<String> expectedList = List.of("SQL", "Git", "JAVA", "Spring");

    // WHEN
    List<String> returnedList = userFormToUserConverter.parseSkillsFromForm(validString);

    // THEN
    assertThat(returnedList).isEqualTo(expectedList);
  }

  @Test
  void parseSkillsFromForm_givenNull_exceptionIsThrown() {

    // GIVEN
    String givenNull = null;

    // WHEN
    Throwable throwable = catchThrowable(() -> userFormToUserConverter.parseSkillsFromForm(givenNull));

    // THEN
    assertThat(throwable).isInstanceOf(NullPointerException.class);
  }
}
