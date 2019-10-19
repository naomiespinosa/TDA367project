package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** A course hold information about the specific course and is user specific. */
public class Course {
  private String name;
  private String courseCode;
  private List<Moment> momentItems = new ArrayList<>();
  private List<ToDo> toDoList = new ArrayList<>();
  private int year;
  private int studyPeriod;
  private boolean isActive;
  private String grade;
  private String ownedBy;
  private int id;

  private static final List acceptedGrades = Arrays.asList("5", "4", "3", "U");

  public Course() {}

  public Course(String name, String courseCode, int year, int studyPeriod) {
    this.name = name;
    this.courseCode = courseCode;
    this.year = year;
    this.studyPeriod = studyPeriod;
    this.isActive = true;
  }

  /**
   * Ends the course giving it a grade
   *
   * @param grade grade
   */
  public void endCourse(String grade) {
    if (grade != null) {
      setGrade(grade);
      isActive = false;
    }
  }

  /** This method activates the course erasing its grade */
  public void reactivateCourse() {
    isActive = true;
    grade = null;
  }
  // Moment

  /**
   * Creates a new moment with a deadline
   *
   * @param name name
   * @param deadline do before this date
   */
  public void newMoment(String name, LocalDate deadline) {
    momentItems.add(new Moment(name, deadline));
  }

  /**
   * Deletes the moment in a specific position
   *
   * @param index index
   */
  public void deleteMoment(int index) {
    momentItems.remove(index);
  }

  public List<Moment> getMomentItems() {
    return momentItems;
  }

  /** Clears all the moments */
  public void clearMomentItems() {
    momentItems.clear();
  }

  // To-Do

  /**
   * Creates a new to-do
   *
   * @param description what does the user want to do
   */
  public void newTodo(String description) {
    toDoList.add(new ToDo(description));
  }

  /**
   * Deletes a to-do in a specific position
   *
   * @param index index
   */
  public void deleteTodo(int index) {
    toDoList.remove(index);
  }

  public List<ToDo> getToDoList() {
    return toDoList;
  }

  public void clearToDoList() {
    toDoList.clear();
  }

  // Setters and Getters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getStudyPeriod() {
    return studyPeriod;
  }

  public void setStudyPeriod(int studyPeriod) {
    this.studyPeriod = studyPeriod;
  }

  public boolean isActive() {
    return isActive;
  }

  private void setGrade(String grade) {
    if (acceptedGrades.contains(grade)) {
      this.grade = grade;
    }
  }

  public String getGrade() {
    return grade;
  }

  public static List getAcceptedGrades() {
    return acceptedGrades;
  }

  public String getOwnedBy() {
    return this.ownedBy;
  }

  public int getId() {
    return this.id;
  }
}
