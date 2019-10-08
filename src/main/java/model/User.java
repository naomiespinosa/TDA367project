package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
  private String username;
  private String name;
  private ArrayList<Course> courses = new ArrayList<>();
  private static User instance = null;

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
    if (instance == null) {
      instance = new User();
    }
    return instance;
  }

  // Used to add a new course
  public void addCourse(String name, String courseCode, int year, int studyPeriod) {
   Course course = new Course(name, courseCode, year, studyPeriod);
   course.newStudySession(new Date(2019,10,8,16,0) , new Date(2019,10,8, 17,25));
    courses.add(course);

  }

  public Course getCourse(int index) {
    return courses.get(index);
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }

  public void clearCourses() {
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
