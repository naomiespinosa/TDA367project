package model.event.timer;

import model.Course;

public abstract class TimerEvent {
  protected Course course;

  public TimerEvent(final Course course) {
    this.course = course;
  }

  public Course getCourse() {
    return course;
  }
}
