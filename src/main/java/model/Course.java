package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// A course hold information about the specific course and is user specific.
public class Course {
  private String name;
  private String courseCode;
  private List<Moment> momentItems;
  private List<StudySession> studySessions;
  private ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
  private int year;
  private int studyPeriod;
  private boolean isActive;

  public Course(
      String name,
      String courseCode,
      List<Moment> momentItems,
      List<StudySession> studySessions,
      ArrayList<ToDo> toDoList,
      int year,
      int studyPeriod) {
    this.name = name;
    this.courseCode = courseCode;
    this.momentItems = momentItems;
    this.studySessions = studySessions;
    this.toDoList = toDoList;
    this.year = year;
    this.studyPeriod = studyPeriod;
    this.isActive = true;
  }

  // TODO - This method is used to end a course and keep the information
  private void endCourse() {
    this.isActive = false;
  }

  // TODO - This method deletes the course and eventually deletes the information? or stores it
  //  somewhere
  private void deleteCourse() {
    // Code
  }

  // List Methods

  // StudySession

  private void newStudySession() {
    studySessions.add(new StudySession());
  }

  private void deleteStudySession(int index) {
    studySessions.remove(index);
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
  private String getName() {
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

  public List<Moment> getMomentItems() {
    return momentItems;
  }

  public void setMomentItems(List<Moment> momentItems) {
    this.momentItems = momentItems;
  }

  public List<StudySession> getStudySessions() {
    return studySessions;
  }

  public void setStudySessions(List<StudySession> studySessions) {
    this.studySessions = studySessions;
  }

  public ArrayList<ToDo> getToDoList() {
    return toDoList;
  }

  public void setToDoList(ArrayList<ToDo> toDoList) {
    this.toDoList = toDoList;
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
