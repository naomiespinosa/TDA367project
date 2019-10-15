package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class StudySession {
  private int id;
  private LocalDateTime start;
  private LocalDateTime stop;
  private int courseId;
  private int ownedBy;

  public LocalDateTime getStart() {
    return this.start;
  }

  public LocalDateTime getStop() {
    return this.stop;
  }

  public Duration getDuration() {
    return Duration.between(
        this.start.toInstant(ZoneOffset.UTC), this.stop.toInstant(ZoneOffset.UTC));
  }

  public int getOwnedBy() {
    return this.ownedBy;
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
    this.start = start;
  }

  public void setStop(final LocalDateTime stop) {
    this.stop = stop;
  }

  public void setCourseId(final int courseId) {
    this.courseId = courseId;
  }

  public void setOwnedBy(final int ownedBy) {
    this.ownedBy = ownedBy;
  }
}
