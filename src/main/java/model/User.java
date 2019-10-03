package model;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String username;
  private String name;
  private List<Course> courses = new ArrayList<>();

  public User() {}

  // Used to add a new course
  public void addCourse(String name, String courseCode, int year, int studyPeriod) {
    courses.add(new Course(name, courseCode, year, studyPeriod));
  }

  public Course getCourse(int index) {
    return courses.get(index);
  }

  public List<Course> getCourses() {
    return courses;
  }

  // Getters och Setters
  private String getUsername() {
    return username;
  }

  private void setUsername(String username) {
    this.username = username;
  }

  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }
}
