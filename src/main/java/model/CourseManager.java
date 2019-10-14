package model;

import java.util.ArrayList;
import java.util.List;
import viewcontroller.Observer;

public class CourseManager {
  private static User user;

  private static List<Observer> observers = new ArrayList<Observer>();

  public void attach(Observer observer) {
    observers.add(observer);
  }

  private static void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  // Deleting courses
  public void deleteCourse(Course course) {
    user.getCourses().remove(course);
    notifyAllObservers();
  }

  // Adding Courses
  public void createNewCourse(String name, String code, int year, int period) {
    user.addCourse(name, code, year, period);
    notifyAllObservers();
  }

  // Editing Courses
  public void changeName(Course course, String name) {
    course.setName(name);
    notifyAllObservers();
  }

  public void changeCode(Course course, String code) {
    course.setCourseCode(code);
    notifyAllObservers();
  }

  public void changeYear(Course course, int year) {
    course.setYear(year);
    notifyAllObservers();
  }

  public void changePeriod(Course course, int period) {
    course.setStudyPeriod(period);
    notifyAllObservers();
  }

  // Status
  public void completeCourse(Course course, String grade) {
    course.endCourse(grade);
    notifyAllObservers();
  }

  public void activateCourse(Course course) {
    course.reactivateCourse();
    notifyAllObservers();
  }

  public void update() {
    notifyAllObservers();
  }
}
