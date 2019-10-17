package model;

import com.google.inject.Inject;
import java.util.List;
import model.repository.CourseRepositoryInterface;
import model.repository.StudySessionRepositoryInterface;

public class User {
  private String username;
  private int id;

  @Inject private CourseRepositoryInterface courseRepository;
  @Inject private StudySessionRepositoryInterface studySessionRepository;

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public int getId() {
    return this.id;
  }

  public List<Course> getCourses() {
    return this.courseRepository.findByUser(this);
  }

  public List<StudySession> getStudySessions() {
    return this.studySessionRepository.findByUser(this);
  }
}
