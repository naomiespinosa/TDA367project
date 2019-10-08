package model.timer.event;

import model.Course;

public abstract class TimerEvent {
  private Course course;

  public TimerEvent(final Course course) {
    this.course = course;
  }

  public Course getCourse() {
    return course;
  }
}
