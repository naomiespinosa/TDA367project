package model.timer;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import model.Course;

public class TimerManager {
  @Inject private EventBus eventBus;

  private Timer activeTimer;

  public Boolean isRunning() {
    return this.activeTimer != null;
  }

  public void start(final Course course) {
    if (this.isRunning() == true) {
      this.activeTimer.stop();
    }

    this.activeTimer = new StudyTimer(course);
    this.activeTimer.start();
  }

  public void stop() {}
}
