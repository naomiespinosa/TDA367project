package model.event.timer;

import java.time.LocalDateTime;
import model.Course;
import model.User;

public class StudyTimerCanceledEvent extends TimerStoppedEvent {
  public StudyTimerCanceledEvent(
      final Course course,
      final Long elapsedSeconds,
      final LocalDateTime startedAt,
      final LocalDateTime stoppedAt,
      final User owner) {
    super(course, elapsedSeconds, startedAt, stoppedAt, owner);
  }
}
