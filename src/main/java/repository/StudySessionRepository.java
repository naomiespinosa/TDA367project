package repository;

import com.google.inject.Inject;
import java.util.List;
import model.StudySession;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

public class StudySessionRepository {
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
        .select("SELECT * FROM studySessions ownedBy = :userId")
        .namedParam("userId", user.getId())
        .listResult(this.objectMappers.forClass(StudySession.class));
  }
}
