package model.timer.event;

import model.Course;

public class StudyTimerStoppedEvent extends TimerStoppedEvent {
  public StudyTimerStoppedEvent(final Course course, final Long elapsedSeconds) {
    super(course, elapsedSeconds);
  }
}
