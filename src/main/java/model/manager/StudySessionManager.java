package model.manager;

import com.google.inject.Inject;
import model.StudySession;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

/**
 * Manages study-sessions where it saves the information of start and stop
 *
 * @author Robin
 */
public class StudySessionManager implements StudySessionManagerInterface {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  /**
   * Creates a new study-session with a start and stop time and saves the information
   *
   * @param studySession a time where the user records studying
   */
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
