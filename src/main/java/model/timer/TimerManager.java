package model.timer;

import java.util.Observable;
import model.Course;

public class TimerManager extends Observable {
  private Timer currentTimer;

  public Boolean isRunning() {
    return this.currentTimer != null;
  }

  public void start(final Course course) {
    if (this.isRunning() == true) {
      this.currentTimer.stop();
    }

    this.currentTimer = new StudyTimer(course);
    this.currentTimer.start();
    this.setChanged();
    this.notifyObservers(this);
  }

  public void stop() {
    this.currentTimer.stop();
    this.currentTimer = null;
    this.setChanged();
    this.notifyObservers(this);
  }
}
