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

  public Contact(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  /**
   * gets the name of the contact
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the email of the contact
   *
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * gets the phone number of the contact
   *
   * @return phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the name of the contact
   *
   * @param name name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * gets the course of the contact
   *
   * @return course
   */
  public Course getCourse() {
    return course;
  }

  /**
   * Sets the course of the contact
   *
   * @param course course
   */
  public void setCourse(Course course) {
    this.course = course;
  }
}
