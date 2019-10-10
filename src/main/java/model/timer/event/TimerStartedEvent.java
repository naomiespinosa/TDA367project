package model.timer.event;

import model.Course;

public abstract class TimerStartedEvent extends TimerEvent {
  public TimerStartedEvent(final Course course) {
    super(course);
  }
}
