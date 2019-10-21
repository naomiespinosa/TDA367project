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
  }
}
