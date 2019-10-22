package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Course define what a course is and what a course can do. A course consist of a name, a course
 * code, a study period and a year. A course have the course specific todos and moments.
 */
public class Course {
  private String name;
  private String courseCode;
  private List<Moment> momentItems = new ArrayList<>();
  private List<StudySession> studySessions = new ArrayList<>();
  private List<ToDo> toDoList = new ArrayList<>();
  private int year;
  private int studyPeriod;
  private boolean isActive;
  private String grade;

  private static final List acceptedGrades = Arrays.asList("5", "4", "3", "U");

  public Course(String name, String courseCode, int year, int studyPeriod) {
    this.name = name;
    this.courseCode = courseCode;
    this.year = year;
    this.studyPeriod = studyPeriod;
    this.isActive = true;
  }

  /**
   * This method is used to end a course and keep the information
   *
   * @param grade, the grade is set
   */
  public void endCourse(String grade) {
    if (grade != null) {
      setGrade(grade);
      isActive = false;
    }
  }

  /** this method is used when you want to make an inactive course active again. */
  public void reactivateCourse() {
    isActive = true;
    grade = null;
  }

  /**
   * cretes a new Moment that has a name and a deadline
   *
   * @param name
   * @param deadline
   */
  public void newMoment(String name, LocalDate deadline) {
    momentItems.add(new Moment(name, deadline));
  }

  /**
   * removing a moment
   *
   * @param index
   */
  public void deleteMoment(int index) {
    momentItems.remove(index);
  }

  /** @return a list with all moments */
  public List<Moment> getMomentItems() {
    return momentItems;
  }

  /** removes all moments */
  public void clearMomentItems() {
    momentItems.clear();
  }

  // To-Do

  /**
   * Creates new tod'o with an description
   *
   * @param description
   */
  public void newTodo(String description) {
    toDoList.add(new ToDo(description));
  }
  /** deletes a tod'o */
  public void deleteTodo(int index) {
    toDoList.remove(index);
  }

  /** @return a list with all */
  public List<ToDo> getToDoList() {
    return toDoList;
  }

  /** removes all tod'os */
  public void clearToDoList() {
    toDoList.clear();
  }

  // Setters and Getters

  /** @return name of the course */
  public String getName() {
    return name;
  }

  /**
   * sets the name of the course
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the course code */
  public String getCourseCode() {
    return courseCode;
  }

  /**
   * sets the coursecode of the course
   *
   * @param courseCode
   */
  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  /** @return the year of the course */
  public int getYear() {
    return year;
  }

  /**
   * Sets the year of the course
   *
   * @param year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /** @return study year of the course */
  public int getStudyPeriod() {
    return studyPeriod;
  }

  /**
   * Sets the study period
   *
   * @param studyPeriod
   */
  public void setStudyPeriod(int studyPeriod) {
    this.studyPeriod = studyPeriod;
  }

  /** @return if the course is active or not */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Set the grade of a course
   *
   * @param grade
   */
  private void setGrade(String grade) {
    if (acceptedGrades.contains(grade)) {
      this.grade = grade;
    }
  }

  /** @return the grade of the course */
  public String getGrade() {
    return grade;
  }

  /** @return the grade who is accepteble (U, 3, 4, 5) */
  public static List getAcceptedGrades() {
    return acceptedGrades;
  }
}
