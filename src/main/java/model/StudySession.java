package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// Represents one StudySession for a specific course
public class StudySession {
  private LocalDateTime startedAt;
  private LocalDateTime stoppedAt;

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public LocalDateTime getStoppedAt() {
    return stoppedAt;
  }

  public Duration getDuration() {
    return Duration.between(
        startedAt.toInstant(ZoneOffset.UTC), stoppedAt.toInstant(ZoneOffset.UTC));
  }

  public void setStart(final LocalDateTime start) {
    startedAt = start;
  }

  public void setStop(final LocalDateTime stop) {
    stoppedAt = stop;
  }
}
