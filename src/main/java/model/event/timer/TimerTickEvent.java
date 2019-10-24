package model.event.timer;

/** Represents 1 second */
public class TimerTickEvent {
  private Long elapsedSeconds;

  public TimerTickEvent(final Long elapsedSeconds) {
    this.elapsedSeconds = elapsedSeconds;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }
}
