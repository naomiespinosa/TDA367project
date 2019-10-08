package model.timer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import model.Course;

abstract class Timer {
  private java.util.Timer timer = new java.util.Timer();

  private Course course;

  private Callback onStart;
  private Callback onCancel;
  private Callback onCompleted;
  private Callback onTick;
  private LocalDateTime startedAt;
  private LocalDateTime stoppedAt;

  private State state = State.INACTIVE;

  private static Long STUDY_SESSION_LENGTH = 25L * 60L * 1000;

  public Timer(final Course course) {
    this.course = course;
  }

  public Course getCourse() {
    return this.course;
  }

  public void start() {
    if (this.state == State.ACTIVE) {
      return;
    }

    this.startedAt = LocalDateTime.now();
    this.state = State.ACTIVE;
    this.timer.schedule(new CallableTask(this.onCompleted), STUDY_SESSION_LENGTH);
    this.timer.schedule(new CallableTask(this.onTick), 1000, 1000);

    if (this.onStart != null) {
      this.onStart.callback();
    }
  }

  public void cancel() {
    if (this.state == State.CANCELED) {
      return;
    }

    this.stoppedAt = LocalDateTime.now();
    this.timer.cancel();
    this.timer.purge();

    if (this.onCancel != null) {
      this.onCancel.callback();
    }
  }

  public void onStart(final Callback callback) {
    this.onStart = callback;
  }

  public void onCancel(final Callback callback) {
    this.onCancel = callback;
  }

  public void onCompleted(final Callback callback) {
    this.onCompleted = callback;
  }

  public void onTick(final Callback callback) {
    this.onTick = callback;
  }

  public interface Callback {
    void callback();
  }

  public Long getElapsedSeconds() {
    if (this.startedAt == null) {
      throw new IllegalStateException();
    }

    if (this.state == State.ACTIVE) {
      return ChronoUnit.SECONDS.between(this.startedAt, LocalDateTime.now());
    }

    return ChronoUnit.SECONDS.between(this.startedAt, this.stoppedAt);
  }

  public enum State {
    ACTIVE,
    INACTIVE,
    CANCELED,
    COMPLETED
  }
}
