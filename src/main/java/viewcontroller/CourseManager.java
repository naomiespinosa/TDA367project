package viewcontroller;

import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.User;

abstract class CourseManager {
  private static User user = User.getInstance();

  // Observer
  private static List<Observer> observers = new ArrayList<Observer>();

  static void attach(Observer observer) {
    observers.add(observer);
  }

  private static void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  // Deleting courses
  static void deleteCourse(Course course) {
    user.getCourses().remove(course);
    notifyAllObservers();
  }

  // Adding Courses
  static void createNewCourse(String name, String code, int year, int period) {
    user.addCourse(name, code, year, period);
    notifyAllObservers();
  }

  // Editing Courses
  static void changeName(Course course, String name) {
    course.setName(name);
    notifyAllObservers();
  }

  static void changeCode(Course course, String code) {
    course.setCourseCode(code);
    notifyAllObservers();
  }

  static void changeYear(Course course, int year) {
    course.setYear(year);
    notifyAllObservers();
  }

  static void changePeriod(Course course, int period) {
    course.setStudyPeriod(period);
    notifyAllObservers();
  }

  // Status
  static void completeCourse(Course course, String grade) {
    course.endCourse(grade);
    notifyAllObservers();
  }

  static void activateCourse(Course course) {
    course.reactivateCourse();
    notifyAllObservers();
  }
}
