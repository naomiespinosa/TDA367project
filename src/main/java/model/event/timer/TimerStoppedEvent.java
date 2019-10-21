package model.event.timer;

import java.time.LocalDateTime;
import model.Course;
import model.User;

public abstract class TimerStoppedEvent extends TimerEvent {
  private Long elapsedSeconds;
  private LocalDateTime startedAt;
  private LocalDateTime stoppedAt;
  private User owner;

  public User getOwner() {
    return owner;
  }

  public TimerStoppedEvent(
      final Course course,
      final Long elapsedSeconds,
      final LocalDateTime startedAt,
      final LocalDateTime stoppedAt,
      final User owner) {
    super(course);
    this.elapsedSeconds = elapsedSeconds;
    this.startedAt = startedAt;
    this.stoppedAt = stoppedAt;
    this.owner = owner;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public LocalDateTime getStoppedAt() {
    return stoppedAt;
  }
}
