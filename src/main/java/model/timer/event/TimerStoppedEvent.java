package model.timer.event;

import model.Course;

public abstract class TimerStoppedEvent {
  private Course course;
  private Long elapsedSeconds;

  public TimerStoppedEvent(final Course course, final Long elapsedSeconds) {
    this.course = course;
    this.elapsedSeconds = elapsedSeconds;
  }

  public Course getCourse() {
    return course;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }
}
