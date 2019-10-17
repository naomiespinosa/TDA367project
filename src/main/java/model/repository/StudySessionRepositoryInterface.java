package model.repository;

import java.util.List;
import model.Course;
import model.StudySession;
import model.User;

public interface StudySessionRepositoryInterface {
  public List<StudySession> findAll();

  public List<StudySession> findByUser(final User user);

  public List<StudySession> findByCourse(final Course course);
}
