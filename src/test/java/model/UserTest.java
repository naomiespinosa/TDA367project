package model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import viewcontroller.UserManger;

class UserTest {
  @Test
  public void courseFunctions() {
    User user = UserManger.getActiveUser();
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
  public void setGetFunctions() {
    User user = UserManger.getActiveUser();

    user.setUsername("Rolf1337");
    assertSame("Rolf1337", user.getUsername());
  }
}
