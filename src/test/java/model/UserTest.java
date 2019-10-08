package model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class UserTest {
  @Test
  void courseFunctions() {
    User user = new User();
    user.addCourse("Funktionell Programmering", "TDA333", 1, 2);
    user.addCourse("Programmering", "TDA333", 1, 2);
    user.addCourse("Mattematisk Analys", "TDA333", 1, 2);
    user.getCourse(2).endCourse();
    user.addCourse("Kommunikation och ingejörskunskap", "TDA333", 1, 2);
    user.addCourse("Hej", "TDA333", 1, 2);

    assertSame(5, user.getCourses().size());
    assertSame("Mattematisk Analys", user.getCourse(2).getName());
    assertSame(false, user.getCourse(2).isActive());
    assertSame(true, user.getCourse(1).isActive());
  }

  @Test
  void setGetFunctions() {
    User user = new User();

    user.setUsername("Rolf1337");
    assertSame("Rolf1337", user.getUsername());

    user.setName("Rolf");
    assertSame("Rolf", user.getName());
  }
}
