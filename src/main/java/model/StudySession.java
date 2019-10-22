package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Represents one StudySession for a specific course
 */
public class StudySession {
  private LocalDateTime startedAt;
  private LocalDateTime stoppedAt;

  /**
   *
   * @return the time and date of when the study session started
   */
  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  /**
   *
   * @return the time and date of when the study session stopped
   */
  public LocalDateTime getStoppedAt() {
    return stoppedAt;
  }

  /**
   * @return the duration of studysession
   */
  public Duration getDuration() {
    return Duration.between(
        startedAt.toInstant(ZoneOffset.UTC), stoppedAt.toInstant(ZoneOffset.UTC));
  }

  /**
   * sets when the study session started
   * @param start
   */
  public void setStart(final LocalDateTime start) {
    startedAt = start;
  }

  /**
   * sets when the study session stopped
   * @param stop
   */
  public void setStop(final LocalDateTime stop) {
    stoppedAt = stop;
  }
}
