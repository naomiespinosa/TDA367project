package model.repository;

import java.util.List;
import model.Course;
import model.User;

public interface CourseRepositoryInterface {
  List<Course> findAll();

  List<Course> findByUser(final User user);
}
