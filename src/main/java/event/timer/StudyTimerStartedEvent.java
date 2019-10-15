package event.timer;

import model.Course;

public class StudyTimerStartedEvent extends TimerStartedEvent {
  public StudyTimerStartedEvent(final Course course) {
    super(course);
  }
}
