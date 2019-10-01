package model;

import java.util.Date;
import java.util.List;

// A course hold information about the specific course and is user specific.
 public class Course {
  private String name;
  private String courseCode;
  private List<Moment> momentItems;
  private List<StudySession> studySessions;
  private List<ToDo> toDoList;
  private int year;
  private int studyPeriod;
  private boolean isActive;
  public Course(String name, String courseCode, int year, int studyPeriod) {
    this.name = name;
    this.courseCode = courseCode;
    this.year = year;
    this.studyPeriod = studyPeriod;
    this.isActive = true;
  }

  // TODO - This method is used to end a course and keep the information
  private void endCourse() {
    this.isActive = false;
  }

  // TODO - This method deletes the course and eventually deletes the information? or stores it
  // somewhere
  private void deleteCourse() {
    // Code
  }

  // Lists

  private void newStudySession() {}

  private void deleteStudySession(int index) {}

  private List<StudySession> getStudySessions() {
    return studySessions;
  }

  private void clearStudySessions() {
    studySessions.clear();
  }

  private void newMoment(String name, Date deadline) {
    momentItems.add(new Moment(name, deadline));
  }

  private void deleteMoment(int index) {
    momentItems.remove(index);
  }

  private List<Moment> getMomentItems() {
    return momentItems;
  }

  private void clearMomentItems() {
    momentItems.clear();
  }

  private void newTodo(String description) {
    toDoList.add(new ToDo(description));
  }

  private void deleteTodo(int index) {
    toDoList.remove(index);
  }

  private List<ToDo> getToDoList() {
    return toDoList;
  }

  private void clearToDoList() {
    toDoList.clear();
  }

  // Setters and Getters
  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  private String getCourseCode() {
    return courseCode;
  }

  private void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  private int getYear() {
    return year;
  }

  private void setYear(int year) {
    this.year = year;
  }

  private int getStudyPeriod() {
    return studyPeriod;
  }

  private void setStudyPeriod(int studyPeriod) {
    this.studyPeriod = studyPeriod;
  }

  public boolean isActive() {
    return isActive;
  }
}
