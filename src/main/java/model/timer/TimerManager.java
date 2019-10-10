package model.timer;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import model.Course;
import model.timer.event.StudyTimerCanceledEvent;
import model.timer.event.StudyTimerCompletedEvent;
import model.timer.event.StudyTimerStartedEvent;
import model.timer.event.TimerTickEvent;

public class TimerManager {
  private Timer activeTimer;

  private EventBus eventBus;

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
                    activeTimer.getCourse(), activeTimer.getElapsedSeconds())));

    activeTimer.onCompleted(
        () ->
            eventBus.post(
                new StudyTimerCompletedEvent(
                    activeTimer.getCourse(), activeTimer.getElapsedSeconds())));

    activeTimer.onTick(
        () -> eventBus.post(new TimerTickEvent(activeTimer.getElapsedSeconds())));

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
}
