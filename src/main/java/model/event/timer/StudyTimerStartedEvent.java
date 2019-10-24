package model.event.timer;

import model.Course;

/**
 * For when the times has started
 */
public class StudyTimerStartedEvent extends TimerStartedEvent {
  public StudyTimerStartedEvent(final Course course) {
    super(course);
  }
}
