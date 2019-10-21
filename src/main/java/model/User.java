package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class User {
  private String name;
  private String passwordHash;
  private int personNumber;
  private List<Course> courses = new ArrayList<>();

  private User(Integer personNumber, String name, String passwordHash) {
    this.personNumber = personNumber;
    this.name = name;
    this.passwordHash = passwordHash;
  }

  static User createUser(Integer personNumber, String name, String password) {
    return new User(personNumber, name, Util.hash(password));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPersonNumber() {
    return personNumber;
  }

  public void setPersonNumber(int id) {
    this.personNumber = id;
  }

  public boolean hasPassword(String password) {
    return passwordHash.equals(Util.hash(password));
  }

  public Iterable<Course> getCourses() {
    return courses;
  }

  public Iterable<Course> filterCourses(Predicate<Course> p) {
    return courses.stream().filter(p).collect(Collectors.toList());
  }

  public void addCourse(Course course) {
    courses.add(course);
  }
}
