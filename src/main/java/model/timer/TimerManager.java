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
    if (this.isRunning() == true) {
      this.activeTimer.cancel();
    }

    this.activeTimer = new StudyTimer(course);

    this.activeTimer.onStart(() -> this.eventBus.post(new StudyTimerStartedEvent(course)));

    this.activeTimer.onCancel(
        () ->
            this.eventBus.post(
                new StudyTimerCanceledEvent(
                    this.activeTimer.getCourse(), this.activeTimer.getElapsedSeconds())));

    this.activeTimer.onCompleted(
        () ->
            this.eventBus.post(
                new StudyTimerCompletedEvent(
                    this.activeTimer.getCourse(), this.activeTimer.getElapsedSeconds())));

    this.activeTimer.onTick(
        () -> this.eventBus.post(new TimerTickEvent(this.activeTimer.getElapsedSeconds())));

    this.activeTimer.start();
  }

  public void cancel() {
    if (this.isRunning() == false) {
      return;
    }

    this.activeTimer.cancel();
    this.activeTimer = null;
  }

  @Subscribe
  public void onStudyTimerCompleted(final StudyTimerCompletedEvent event) {
    this.activeTimer.cancel();
  }
}
