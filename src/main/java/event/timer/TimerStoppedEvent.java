package event.timer;

import model.Course;

public abstract class TimerStoppedEvent extends TimerEvent {
  private Long elapsedSeconds;

  public TimerStoppedEvent(final Course course, final Long elapsedSeconds) {
    super(course);
    this.elapsedSeconds = elapsedSeconds;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }
}
