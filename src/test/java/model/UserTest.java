package model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class UserTest {
  @Test
  void courseFunctions() {
    User user = User.getInstance();
    user.clearCourses();
    user.addCourse("Funktionell Programmering", "TDA333", 1, 2);
    user.addCourse("Programmering", "TDA333", 1, 2);
    user.addCourse("Mattematisk Analys", "TDA333", 1, 2);
    user.getCourse(2).endCourse("4");
    user.addCourse("Kommunikation och ingejörskunskap", "TDA333", 1, 2);
    user.addCourse("Hej", "TDA333", 1, 2);

    assertSame(5, user.getCourses().size());
    assertSame("Mattematisk Analys", user.getCourse(2).getName());
    assertSame(false, user.getCourse(2).isActive());
    assertSame(true, user.getCourse(1).isActive());
  }

  @Test
  void setGetFunctions() {
    User user = User.getInstance();

    user.setUsername("Rolf1337");
    assertSame("Rolf1337", user.getUsername());

    user.setName("Rolf");
    assertSame("Rolf", user.getName());
  }

  @Test
  void testSingleton() {
    User user = User.getInstance();
    user.clearCourses();
    user.addCourse("Funktionell Programmering", "TDA333", 1, 2);
    user.addCourse("Programmering", "TDA333", 1, 2);
    user.getCourse(1).endCourse("5");
    user.setName("Rolf");

    User user2 = User.getInstance();
    assertSame(user.getCourse(1), user2.getCourse(1));
    assertSame(user.getName(), user2.getName());
    assertSame(user2.getCourse(1).isActive(), user.getCourse(1).isActive());
  }
}
