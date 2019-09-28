package model.timer;

import model.Course;

public class StudyTimer implements Timer {
  private java.util.Timer timer;

  private Course course;

  public StudyTimer(final Course course) {
    this.course = course;
    this.timer = new java.util.Timer();
  }

  public void start() {
    this.timer.schedule(new StudyTask(), StudyTask.INTERVAL_IN_MILLISECONDS);
  }

  public void stop() {
    this.course = null;
    this.timer.cancel();
  }

  public Course getcourse() {
    return this.course;
  }
}
