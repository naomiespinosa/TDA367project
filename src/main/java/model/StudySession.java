package model;

import java.time.Duration;
import java.util.Date;

class StudySession {
  private Date start;
  private Date stop;
  private Course course;

  public StudySession(final Date start, final Date stop, final Course course) {
    this.start = start;
    this.stop = stop;
    this.course = course;
  }

  public Date getStart() {
    return this.start;
  }

  public Date getStop() {
    return this.stop;
  }

  public Duration getDuration() {
    return Duration.between(this.start.toInstant(), this.stop.toInstant());
  }

  public Course getCourse() {
    return this.course;
  }
}
