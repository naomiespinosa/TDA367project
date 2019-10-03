package model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class CourseTest {

  // Test to see if we can add courses and if they are being set correctly
  @Test
  public void addCourseTest() {
    User user = new User();
    user.addCourse("tda", "333", 1, 1);
    user.addCourse("tdaa", "3333", 2, 2);
    assertSame(2, user.getCourses().size());
  }
}