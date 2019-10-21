package model.manager;

import model.Course;

/**
 * Keeps track of any active timers and dispatches events of any changes to the timer, such as start
 * and stop.
 */
public interface TimerManagerInterface {

  /**
   * Returns true if any timer is currently active, otherwise false.
   *
   * @return True if any timer is currently active, otherwise false.
   */
  Boolean isRunning();

  /**
   * Starts a timer for course.
   *
   * @param course The associated course.
   */
  void start(final Course course);

  /** Cancels any currently active timer. If no active timer exists no action is taken. */
  void cancel();
}
