package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {

  Contact contact = new Contact("Johanna", "j@live.se", "0707");

  @Test
  public void contactName() {
    assertEquals("Johanna", contact.getName());
    contact.setName("Hanna");
    assertEquals("Hanna", contact.getName());
  }

  @Test
  public void contactEmail() {
    assertEquals("j@live.se", contact.getEmail());
  }

  @Test
  public void contactPhone() {
    assertEquals("0707", contact.getPhoneNumber());
  }

  @Test
  public void contactCourse() {
    Course course = new Course("Hej", "TDA111", 2019, 2);
    contact.setCourse(course);
    assertEquals("Hej", contact.getCourse().getName());
  }

  @Test
  void toStringTest() {
    assertEquals("Johanna", contact.toString());
  }
}
