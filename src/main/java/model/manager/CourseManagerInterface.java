package model.manager;

import model.Course;
import model.Observable;
import model.User;

/** Manages courses. */
public interface CourseManagerInterface extends Observable {

  /**
   * Deletes a course.
   *
   * @param course The course to the deleted.
   */
  void deleteCourse(final Course course);

  /**
   * Creates a new course and persists it to the database.
   *
   * @param name Name of the course.
   * @param code 6 alphanumerical combination of the course.
   * @param year Year of the user enrollment on the course.
   * @param period Study period of the user enrollment.
   * @param user The user enrolling to the course.
   */
  void createNewCourse(String name, String code, int year, int period, final User user);

  /**
   * Saves the course and persists it to the database.
   *
   * @param course The course to be saved.
   */
  void save(Course course);
}
