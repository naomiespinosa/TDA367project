package model;

/**
 * The contact class defines what a contact is and what a contact can do. A contact consist of a
 * name, an email address, a phone number, what course it’s related to and what title it has
 * (Student, teacher…).
 *
 * @author Johanna
 */
public class Contact {

  private String name;
  private String email;
  private String phoneNumber;
  private Course course;
  private String titel;

  public Contact(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
