package model.repository;

import java.util.List;
import model.Course;
import model.StudySession;
import model.User;

/** Searches the database for study sessions. */
public interface StudySessionRepositoryInterface {

  /**
   * Finds all study sessions.
   *
   * @return All study sessions.
   */
  public List<StudySession> findAll();

  /**
   * Finds all study sessions for user.
   *
   * @param user The users to search for.
   * @return All study sessions for user.
   */
  public List<StudySession> findByUser(final User user);

  /**
   * Finds all study sessions for course.
   *
   * @param course The course to search for.
   * @return All study sessions for course.
   */
  public List<StudySession> findByCourse(final Course course);
}
