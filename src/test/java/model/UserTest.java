package model;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class UserTest {
  @Test
  public void setGetFunctions() {
    User user = new User();

    user.setUsername("Rolf1337");
    assertSame("Rolf1337", user.getUsername());
  }
}
