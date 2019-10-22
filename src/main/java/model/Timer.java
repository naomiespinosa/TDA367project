package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Timer {
  private java.util.Timer timer = new java.util.Timer();

  private Course course;

  private Callback onStart;
  private Callback onCancel;
  private Callback onCompleted;
  private Callback onTick;
  private LocalDateTime startedAt;
  private LocalDateTime stoppedAt;

  private State state = State.INACTIVE;

  private static Long STUDY_SESSION_LENGTH = 25L * 60L * 1000L;

  public Timer(final Course course) {
    this.course = course;
  }

  public Course getCourse() {
    return course;
  }

  public void start() {
    if (state == State.ACTIVE) {
      return;
    }

    startedAt = LocalDateTime.now();
    state = State.ACTIVE;
    timer.schedule(new CallableTask(onCompleted), STUDY_SESSION_LENGTH);
    timer.schedule(new CallableTask(onTick), 1000, 1000);

    if (onStart != null) {
      onStart.callback();
    }
  }

  public void cancel() {
    if (state == State.CANCELED) {
      return;
    }

    stoppedAt = LocalDateTime.now();
    timer.cancel();
    timer.purge();
    this.state = State.CANCELED;

    if (onCancel != null) {
      onCancel.callback();
    }
  }

  public void onStart(final Callback callback) {
    this.onStart = callback;
  }

  public void onCancel(final Callback callback) {
    this.onCancel = callback;
  }

  public void onCompleted(final Callback callback) {
    stoppedAt = LocalDateTime.now();
    this.onCompleted = callback;
  }

  public void onTick(final Callback callback) {
    this.onTick = callback;
  }

  public interface Callback {
    void callback();
  }

  public Long getElapsedSeconds() {
    if (startedAt == null) {
      throw new IllegalStateException();
    }

    if (state == State.ACTIVE) {
      return ChronoUnit.SECONDS.between(startedAt, LocalDateTime.now());
    }

    return ChronoUnit.SECONDS.between(startedAt, stoppedAt);
  }

  public State getState() {
    return this.state;
  }

  public enum State {
    ACTIVE,
    INACTIVE,
    CANCELED,
    COMPLETED
  }
}
