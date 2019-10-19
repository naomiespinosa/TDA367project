package model.event;

import model.Course;

public class CourseSelectedEvent {
  private Course course;

  public CourseSelectedEvent(final Course course) {
    this.course = course;
  }

  public Course getCourse() {
    return this.course;
  }
}
