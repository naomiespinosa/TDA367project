package model.manager;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import model.Course;
import model.StudyTimer;
import model.Timer;
import model.User;
import model.event.UserChangedEvent;
import model.event.timer.StudyTimerCanceledEvent;
import model.event.timer.StudyTimerCompletedEvent;
import model.event.timer.StudyTimerStartedEvent;
import model.event.timer.TimerTickEvent;

/** @author Robin */
public class TimerManager {
  private Timer activeTimer;

  private EventBus eventBus;

  private User user;

  @Inject
  public TimerManager(final EventBus eventBus) {
    this.eventBus = eventBus;
    this.eventBus.register(this);
  }

  public Boolean isRunning() {
    return this.activeTimer != null;
  }

  public void start(final Course course) {
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
                    this.user)));

    activeTimer.onCompleted(
        () ->
            eventBus.post(
                new StudyTimerCompletedEvent(
                    activeTimer.getCourse(),
                    activeTimer.getElapsedSeconds(),
                    activeTimer.getStartedAt(),
                    activeTimer.getStoppedAt(),
                    this.user)));

    activeTimer.onTick(() -> eventBus.post(new TimerTickEvent(activeTimer.getElapsedSeconds())));

    activeTimer.start();
  }

  public void cancel() {
    if (isRunning() == false) {
      return;
    }

    activeTimer.cancel();
    activeTimer = null;
  }

  @Subscribe
  public void onStudyTimerCompleted(final StudyTimerCompletedEvent event) {
    activeTimer.cancel();
  }

  @Subscribe
  private void onUserChange(final UserChangedEvent userChangedEvent) {
    this.user = userChangedEvent.getNewUser();
  }
}
