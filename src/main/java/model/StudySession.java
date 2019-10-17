package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class StudySession {
  private int id;
  private LocalDateTime startedAt;
  private LocalDateTime stoppedAt;
  private int courseId;

  public LocalDateTime getStartedAt() {
    return this.startedAt;
  }

  public LocalDateTime getStoppedAt() {
    return this.stoppedAt;
  }

  public Duration getDuration() {
    return Duration.between(
        this.startedAt.toInstant(ZoneOffset.UTC), this.stoppedAt.toInstant(ZoneOffset.UTC));
  }

  public int getCourseId() {
    return this.courseId;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setStart(final LocalDateTime start) {
    this.startedAt = start;
  }

  public void setStop(final LocalDateTime stop) {
    this.stoppedAt = stop;
  }

  public void setCourseId(final int courseId) {
    this.courseId = courseId;
  }
}
