package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The user class defines what a user is and what it can do. The user consist of a name, a
 * personalNumber and a password. The user has a collection of courses.
 */
public class User {
  private String name;
  private String passwordHash;
  private int personNumber;
  private List<Course> courses = new ArrayList<>();
  private List<Contact> contacts = new ArrayList<>();

  private User(Integer personNumber, String name, String passwordHash) {
    this.personNumber = personNumber;
    this.name = name;
    this.passwordHash = passwordHash;
  }

  /**
   * Creates a user
   *
   * @param personNumber personNumber
   * @param name name
   * @param password password
   * @return the user
   */
  static User createUser(Integer personNumber, String name, String password) {
    return new User(personNumber, name, Util.hash(password));
  }
  /** @return the description/name of the user */
  public String getName() {
    return name;
  }
  /**
   * sets the name/description of the user
   *
   * @param name name
   */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the personalnumber of the user */
  public int getPersonNumber() {
    return personNumber;
  }

  /**
   * checks if the password matches the hashpassword
   *
   * @param password password
   * @return a boolean
   */
  public boolean hasPassword(String password) {
    return passwordHash.equals(Util.hash(password));
  }

  /** @return a copy of a list with courses */
  public Iterable<Course> getCourses() {
    return courses;
  }
  /**
   * Filter the courses
   *
   * @return a copy of a list with courses
   */
  Iterable<Course> filterCourses(Predicate<Course> p) {
    return courses.stream().filter(p).collect(Collectors.toList());
  }

  void addCourse(Course course) {
    courses.add(course);
  }

  void deleteCourse(Course course) {
    courses.remove(course);
  }

  void addContact(Contact contact) {
    contacts.add(contact);
  }

  void removeContacts(Contact contact) {
    contacts.remove(contact);
  }

  List<Contact> getContactList() {
    return contacts;
  }
}
