package model.repository;

import java.util.List;
import model.Course;
import model.User;

/** Searches the database for courses. */
public interface CourseRepositoryInterface {

  /**
   * Returns all courses regardless of status.
   *
   * @return All courses regardless of status.
   */
  List<Course> findAll();

  /**
   * Finds all courses for user.
   *
   * @param user The user to search for.
   * @return All courses for user.
   */
  List<Course> findByUser(final User user);
}
