package model.timer;

import model.Course;
import model.timer.event.StudyTimerStartedEvent;

public class StudyTimer extends Timer {
  public StudyTimer(final Course course) {
    super(course);
  }

  public void start() {
    super.start();
    this.eventBus.post(new StudyTimerStartedEvent(this.course));
  }

  public void stop() {
    super.stop();
  }
}
