package model;

import java.time.Duration;
import java.util.Date;

// Represents one StudySession for a specific course
public class StudySession {
  private Date start;
  private Date stop;
  private Course course;

  public StudySession(final Date start, final Date stop, final Course course) {
    this.start = start;
    this.stop = stop;
    this.course = course;
  }

  public Date getStart() {
    return start;
  }

  public Date getStop() {
    return stop;
  }

  public Duration getDuration() {
    return Duration.between(start.toInstant(), stop.toInstant());
  }

  public Course getCourse() {
    return course;
  }
}
