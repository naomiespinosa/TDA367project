package model.event.timer;

public class TimerTickEvent {
  private Long elapsedSeconds;

  public TimerTickEvent(final Long elapsedSeconds) {
    this.elapsedSeconds = elapsedSeconds;
  }

  public Long getElapsedSeconds() {
    return elapsedSeconds;
  }
}
