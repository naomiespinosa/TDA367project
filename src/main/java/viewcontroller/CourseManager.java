package viewcontroller;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.User;

abstract class CourseManager {
  private static User user = User.getInstance();

  // Observer
  private static List<Observer> observers = new ArrayList<Observer>();
  private int state;

  static void attach(Observer observer) {
    observers.add(observer);
  }

  static void createNewCourse(String name, String code, int year, int period) {
    user.addCourse(name, code, year, period);
    notifyAllObservers();
  }

  private static void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
  // Adding Courses


  // GetCourses
  public static List<Course> getCourses(){
    return user.getCourses();
  }
}