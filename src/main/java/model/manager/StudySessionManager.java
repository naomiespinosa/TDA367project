package model.manager;

import com.google.inject.Inject;
import model.StudySession;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

/** @author Robin Hallabro-Kokko <robin@hallabro.nu> */
public class StudySessionManager implements StudySessionManagerInterface {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  public void create(final StudySession studySession) {
    this.fluentJdbc
        .query()
        .update(
            "INSERT INTO studySessions (startedAt, stoppedAt, courseId, ownedBy) VALUES (:startedAt, :stoppedAt, :courseId, :ownedBy)")
        .namedParam("startedAt", studySession.getStartedAt())
        .namedParam("stoppedAt", studySession.getStoppedAt())
        .namedParam("courseId", studySession.getCourseId())
        .run();
  }
}
