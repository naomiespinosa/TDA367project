package model.timer.event;

import model.Course;

public abstract class TimerStartedEvent {
  private Course course;

  public TimerStartedEvent(final Course course) {
    this.course = course;
  }

  public Course getCourse() {
    return course;
  }
}
