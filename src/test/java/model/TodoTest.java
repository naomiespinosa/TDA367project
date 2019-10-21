package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

  @Test
  public void todoName() {
    ToDo todo = new ToDo("Read page 2");
    todo.toString();

    assertEquals("Read page 2", todo.toString());
    assertEquals("Read page 2", todo.getDescription());
  }
}
