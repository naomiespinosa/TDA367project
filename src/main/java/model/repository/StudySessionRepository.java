package model.repository;

import com.google.inject.Inject;
import java.util.List;
import model.Course;
import model.StudySession;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

public class StudySessionRepository implements StudySessionRepositoryInterface {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  public List<StudySession> findAll() {
    return this.fluentJdbc
        .query()
        .select("SELECT * FROM studySessions")
        .listResult(this.objectMappers.forClass(StudySession.class));
  }

  public List<StudySession> findByUser(final User user) {
    return this.fluentJdbc
        .query()
        .select("SELECT * FROM studySessions JOIN users ON users.id WHERE users.id = :userId")
        .namedParam("userId", user.getId())
        .listResult(this.objectMappers.forClass(StudySession.class));
  }

  public List<StudySession> findByCourse(final Course course) {
    return this.fluentJdbc
        .query()
        .select("SELECT * FROM studySessions WHERE courseId = :courseId")
        .namedParam("courseId", course.getId())
        .listResult(this.objectMappers.forClass(StudySession.class));
  }
}
