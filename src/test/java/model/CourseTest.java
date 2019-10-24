package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// Testing for model.Course
class CourseTest {
  private static Min5a model;

  @BeforeAll
  static void setup() {
    model = Min5a.createMin5a();
    model.login(TestData.number, TestData.pwd);
  }

  @Test
  void whenCourseIsCreated() {
    Course course = new Course("Programmering", "TDA367", 2019, 4);
    assertTrue(course.isActive());
    assertNull(course.getGrade());
    assertEquals(0, course.getMomentItems().size());
    assertEquals(0, course.getToDoList().size());
  }

  @Test
  public void deleteCourse() {
    Course course = new Course("namn", "tda345", 34, 3);
    course.endCourse("3");
    assertFalse(course.isActive());
    assertEquals("3", course.getGrade());
    assertEquals(Arrays.asList("5", "4", "3", "U"), Course.getAcceptedGrades());
  }

  @Test
  public void reactivateCourse() {
    Course course = new Course("namn", "tda345", 34, 3);
    course.endCourse("4");
    course.reactivateCourse();
    assertTrue(course.isActive());
  }

  @Test
  public void todo() {
    Course course = new Course("namn", "tda345", 34, 3);
    course.newTodo("Read");
    assertEquals(course.getToDoList().size(), 1);
    course.deleteTodo(0);
    assertEquals(course.getToDoList().size(), 0);

    course.newTodo("Read");
    course.newTodo("Read");
    course.clearToDoList();
    assertEquals(course.getToDoList().size(), 0);
  }

  @Test
  public void moment() {
    Course course = new Course("namn", "tda345", 34, 3);
    course.newMoment("tenta", LocalDate.of(2017, 3, 14));
    course.newMoment("tenta", LocalDate.of(2017, 3, 14));
    course.deleteMoment(0);

    assertEquals(1, course.getMomentItems().size());

    course.clearMomentItems();

    assertEquals(0, course.getMomentItems().size());
  }
}
