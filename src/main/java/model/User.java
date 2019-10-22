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

  User(Integer personNumber, String name, String passwordHash) {
    this.personNumber = personNumber;
    this.name = name;
    this.passwordHash = passwordHash;
  }

  /**
   * Creates a user
   *
   * @param personNumber
   * @param name
   * @param password
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
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the personalnumber of the user */
  public int getPersonNumber() {
    return personNumber;
  }

  public void setPersonNumber(int id) {
    this.personNumber = id;
  }

  /**
   * checks if the password matches the hashpassword
   *
   * @param password
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
  public Iterable<Course> filterCourses(Predicate<Course> p) {
    return courses.stream().filter(p).collect(Collectors.toList());
  }

  /**
   * Adss a course to a list
   *
   * @param course
   */
  public void addCourse(Course course) {
    courses.add(course);
  }

  public void deleteCourse(Course course) {
    courses.remove(course);
  }
}
