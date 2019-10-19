package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;

// A course hold information about the specific course and is user specific.
public class Course {
  private String name;
  private String courseCode;
  private List<Moment> momentItems = new ArrayList<>();
  private List<StudySession> studySessions = new ArrayList<>();
  private List<ToDo> toDoList = new ArrayList<>();
  private int year;
  private int studyPeriod;
  private boolean isActive;
  private Image image = null;

  public Course(String name, String courseCode, int year, int studyPeriod) {
    this.name = name;
    this.courseCode = courseCode;
    this.year = year;
    this.studyPeriod = studyPeriod;
    this.isActive = true;
  }

  // TODO - This method is used to end a course and keep the information
  public void endCourse() {
    this.isActive = false;
  }

  // TODO - This method deletes the course and eventually deletes the information? or stores it
  // somewhere
  private void deleteCourse() {
    // Code
  }

  // List Methods

  // StudySession

  private void newStudySession() {}

  private void deleteStudySession(int index) {}

  private List<StudySession> getStudySessions() {
    return studySessions;
  }

  private void clearStudySessions() {
    studySessions.clear();
  }

  // Moment

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

  // To-Do

  public void newTodo(String description) {
    toDoList.add(new ToDo(description));
  }

  public void deleteTodo(int index) {
    toDoList.remove(index);
  }

  public void clearToDoList() {
    toDoList.clear();
  }

  // Setters and Getters
  public String getName() {
    return name;
  }

  public javafx.scene.image.Image getImage() {
    return image;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getCourseCode() {
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

  @Override
  public String toString() {
    return this.getName();
  }

  private void setStudyPeriod(int studyPeriod) {
    this.studyPeriod = studyPeriod;
  }

  public boolean isActive() {
    return isActive;
  }
}
