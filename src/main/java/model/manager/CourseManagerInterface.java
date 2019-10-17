package model.manager;

import model.Course;
import model.Observable;
import model.User;

public interface CourseManagerInterface extends Observable {
  public void deleteCourse(final Course course);

  public void createNewCourse(String name, String code, int year, int period, final User user);

  public void save(Course course);
}
