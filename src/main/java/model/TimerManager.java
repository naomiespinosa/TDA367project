package model.manager;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import model.Course;
import model.StudyTimer;
import model.Timer;
import model.User;
import model.event.timer.StudyTimerCanceledEvent;
import model.event.timer.StudyTimerCompletedEvent;
import model.event.timer.StudyTimerStartedEvent;
import model.event.timer.TimerTickEvent;

public class TimerManager {
  private Timer activeTimer;
  private EventBus eventBus;

  /**
   * Constructor.
   *
   * @param eventBus The event bus.
   */
  public TimerManager(final EventBus eventBus) {
    this.eventBus = eventBus;
    this.eventBus.register(this);
  }

  /**
   * Returns true if any active timer is running, otherwise false.
   *
   * @return True if any active timer is running, otherwise false.
   */
  public Boolean isRunning() {
    return this.activeTimer != null;
  }

  /**
   * Starts a timer for the selected course and user.
   *
   * @param course The active course.
   * @param user The active user.
   */
  public void start(final Course course, final User user) {
    if (isRunning() == true) {
      activeTimer.cancel();
    }

    activeTimer = new StudyTimer(course);

    activeTimer.onStart(() -> eventBus.post(new StudyTimerStartedEvent(course)));

    activeTimer.onCancel(
        () ->
            eventBus.post(
                new StudyTimerCanceledEvent(
                    activeTimer.getCourse(),
                    activeTimer.getElapsedSeconds(),
                    activeTimer.getStartedAt(),
                    activeTimer.getStoppedAt(),
                    user)));

    activeTimer.onCompleted(
        () ->
            eventBus.post(
                new StudyTimerCompletedEvent(
                    activeTimer.getCourse(),
                    activeTimer.getElapsedSeconds(),
                    activeTimer.getStartedAt(),
                    activeTimer.getStoppedAt(),
                    user)));

    activeTimer.onTick(() -> eventBus.post(new TimerTickEvent(activeTimer.getElapsedSeconds())));

    activeTimer.start();
  }

  /** Cancels any running timer. */
  public void cancel() {
    if (isRunning() == false) {
      return;
    }

    activeTimer.cancel();
    activeTimer = null;
  }

  /**
   * Called when a timer is completed.
   *
   * @param event The timer event.
   */
  @Subscribe
  public void onStudyTimerCompleted(final StudyTimerCompletedEvent event) {
    activeTimer.cancel();
  }
}
