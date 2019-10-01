package model.timer;

import com.google.common.base.Stopwatch;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.util.concurrent.TimeUnit;
import model.Course;
import model.timer.event.StudyTimerStartedEvent;
import model.timer.event.StudyTimerStoppedEvent;

abstract class Timer {
  private Stopwatch stopWatch = new Stopwatch();

  @Inject protected EventBus eventBus;

  protected Course course;

  public Timer(final Course course) {
    this.course = course;
  }

  public void start() {
    this.stopWatch.start();
  }

  public void stop() {
    this.stopWatch.stop();
  }
}
