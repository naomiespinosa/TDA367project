package model.timer;

import model.Course;

public class BreakTimer implements Timer {
  private java.util.Timer timer;

  public void start() {
    this.timer.schedule(new BreakTask(), BreakTask.INTERVAL_IN_MILLISECONDS);
  }

  public void stop() {
    this.timer.cancel();
  }
}
