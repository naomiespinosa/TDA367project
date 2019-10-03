package model;

import java.util.ArrayList;
import java.util.List;

// Suposedly Good for OPC since if we want to add the feature of having multiple user this clas will
// be open for extension
// Users have their own classes that are not shared between users.
public class User {
  private String username;
  private String name;
  private List<Course> courses;

  public User(String username, String name) {
    this.username = username;
    this.name = name;
  }

  // Used to add a new course
  private void addCourse(
          String name,
          String courseCode,
          List<Moment> moments,
          List<StudySession> studySessions,
          ArrayList<ToDo> toDoList,
          int year,
          int studyPeriod) {
    courses.add(new Course(name, courseCode, moments, studySessions, toDoList, year, studyPeriod));
  }

  // Returns specific course
  private Course getCourse(int index) {
    return courses.get(index);
  }

  // TODO - Without
  private void startStudySession(int index) {}

  // Getters och Setters
  private List<Course> getCourses() {
    return courses;
  }

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
