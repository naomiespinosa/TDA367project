package event.timer;

import model.Course;

public class StudyTimerCanceledEvent extends TimerStoppedEvent {
  public StudyTimerCanceledEvent(final Course course, final Long elapsedSeconds) {
    super(course, elapsedSeconds);
  }
}