package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {

  @Test
  public void contactName() {
    Contact contact = new Contact("Johanna", "j@live.se", "0707");
    assertEquals("Johanna", contact.getName());
  }

  @Test
  public void contactEmail() {
    Contact contact = new Contact("Johanna", "j@live.se", "0707");
    assertEquals("j@live.se", contact.getEmail());
  }

  @Test
  public void contactPhone() {
    Contact contact = new Contact("Johanna", "j@live.se", "0707");
    assertEquals("0707", contact.getPhoneNumber());
  }

  @Test
  public void contactCourse() {
    Course course = new Course("Hej", "TDA111", 2019, 2);
    Contact contact = new Contact("Johanna", "j@live.se", "0707");
    contact.setCourse(course);
    assertEquals("Hej", contact.getCourse().getName());
  }

  @Test
  public void contactToString() {
    Contact contact = new Contact("Johanna", "j@live.se", "0707");
    assertEquals("Johanna", contact.toString());
  }
}
