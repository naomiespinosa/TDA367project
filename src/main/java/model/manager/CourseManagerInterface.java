package model.manager;

import model.Course;
import model.Observable;
import model.User;

public interface CourseManagerInterface extends Observable {
  void deleteCourse(final Course course);

  void createNewCourse(String name, String code, int year, int period, final User user);

  void save(Course course);
}
