package model;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String username;
  private String name;
  private List<Course> courses = new ArrayList<>();
  private static User instance = null;

  private User() {

  }

  // TEMPORARY FOR TESTING ONLY
  public void testing() {
    addCourse("Funktionell Programmering", "TDA333", 1, 2);
    addCourse("Programmering", "TDA333", 1, 2);
    addCourse("Mattematisk Analys", "TDA333", 1, 2);
    getCourse(2).endCourse();
    addCourse("Kommunikation och ingejörskunskap", "TDA333", 1, 2);
    addCourse("Hej", "TDA333", 1, 2);
  }

  public static User getInstance() {
    if(instance == null) {
      instance = new User();
    }
    return instance;
  }

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

  public void clearCourses(){
      courses.clear();
  }

  // Getters och Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
