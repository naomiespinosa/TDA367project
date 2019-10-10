package model.timer.event;

import model.Course;

public class StudyTimerCompletedEvent extends TimerStoppedEvent {
  public StudyTimerCompletedEvent(final Course course, final Long elapsedSeconds) {
    super(course, elapsedSeconds);
  }
}
