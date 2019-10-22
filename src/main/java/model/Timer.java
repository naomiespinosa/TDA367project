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

  /**
   * Returns the date and time the timer was started at.
   *
   * @return The date and time the timer was started at.
   */
  public LocalDateTime getStartedAt() {
    return this.startedAt;
  }

  /**
   * Returns the date and time the timer was stopped at.
   *
   * @return The date and time the timer was sopped at.
   */
  public LocalDateTime getStoppedAt() {
    return this.stoppedAt;
  }

  private State state = State.INACTIVE;
  private static Long STUDY_SESSION_LENGTH = 25L * 60L * 1000L;

  /**
   * Constructor.
   *
   * @param course The associated course.
   */
  public Timer(final Course course) {
    this.course = course;
  }

  /**
   * Gets the associated course.
   *
   * @return The accociated course.
   */
  public Course getCourse() {
    return course;
  }

  /** Starts the timer. Does nothing if the timer is already running. */
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

  /** Cancels the active timer. Nothing happens if timer is already canceled. */
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

  /**
   * Callback for when the timer is started.
   *
   * @param callback The callback to be executed.
   */
  public void onStart(final Callback callback) {
    this.onStart = callback;
  }

  /**
   * Callback for when the timer is canceled.
   *
   * @param callback The callback to be executed.
   */
  public void onCancel(final Callback callback) {
    this.onCancel = callback;
  }

  /**
   * Callback for when the timer is completed.
   *
   * @param callback The callback to be executed.
   */
  public void onCompleted(final Callback callback) {
    stoppedAt = LocalDateTime.now();
    this.onCompleted = callback;
  }

  /**
   * Callback for when the timer ticks (every second).
   *
   * @param callback The callback to be executed.
   */
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
