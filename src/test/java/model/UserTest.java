package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserTest {
  private static Min5a model;

  @BeforeAll
  static void setup() {
    model = Min5a.createMin5a(); // Start with a clean model
  }

  @Test
  public void testUsers() {
    int size = 3;
    TestData.insertUsers(size, model);

    List<User> users = model.getUsers();
    assertEquals(size, users.size());

    for (int i = 0; i < size; i++) {
      User user = users.get(i);
      assertEquals(TestData.number + i, user.getPersonNumber());
      assertEquals(TestData.name, user.getName());
      assertTrue(user.hasPassword(TestData.pwd));
    }
  }

  @Test
  public void testLogin() {
    assertFalse(model.login(TestData.number, TestData.pwd));
    TestData.insertUsers(1, model);
    assertTrue(model.login(TestData.number, TestData.pwd));

    assertFalse(model.isUserUnique(TestData.number));
  }

  @Test
  void userCoursesTest() {
    Course course = new Course("Math", "TDA555", 2019, 4);
    User user = User.createUser(23, "Mac", "qwerty");
    user.addCourse(course);
    assertTrue(user.getCourses().iterator().hasNext());

    user.deleteCourse(course);
    assertFalse(user.getCourses().iterator().hasNext());
  }

  @Test
  void userContactsTest() {
    Contact contact = new Contact("Max", "max@email.com", "0768763523");
    User user = User.createUser(23, "Mac", "qwerty");

    user.addContact(contact);
    assertEquals(1, user.getContactList().size());
    assertSame(contact, user.getContactList().get(0));

    user.removeContacts(contact);
    assertEquals(0, user.getContactList().size());
  }

  @Test
  void setUserName() {
    User user = User.createUser(23, "Mac", "qwerty");
    user.setName("Marcus");
    assertSame("Marcus", user.getName());
  }

  @Test
  void activeUserTests() {
    Course course = new Course("Math", "TDA555", 2019, 4);
    Contact contact = new Contact("Max", "max@email.com", "0768763523");

    model.login(TestData.number, TestData.pwd);
    assertSame("Sten och Stanley", model.getActiveUserName());

    model.setActiveUserName("Max");
    assertSame("Max", model.getActiveUserName());

    model.addCourse("Math", "TDA555", 2019, 4);
    assertTrue(model.getCourses().iterator().hasNext());
    assertSame("Math", model.getActiveCourseName().get(0));

    model.addContact(contact);
    assertSame(1, model.getSavedContacts().size());
    assertSame(contact, model.getActiveUser().getContactList().get(0));

    model.removeContact(contact);
    assertSame(0, model.getSavedContacts().size());

    model.setCourseInactive(course, "3");
    assertFalse(course.isActive());

    model.setCourseActive(course);
    assertTrue(course.isActive());
  }
}
