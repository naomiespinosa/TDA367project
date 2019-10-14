package repository;

import com.google.inject.Inject;
import java.util.List;
import model.Course;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

public class CourseRepository {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  public List<Course> findAll() {
    return this.fluentJdbc
        .query()
        .select("SELECT * FROM courses")
        .listResult(this.objectMappers.forClass(Course.class));
  }

  public List<Course> findByUser(final User user) {
    return this.fluentJdbc
        .query()
        .select("SELECT * FROM courses WHERE ownedBy = :userId")
        .namedParam("userId", user.getId())
        .listResult(this.objectMappers.forClass(Course.class));
  }
}
