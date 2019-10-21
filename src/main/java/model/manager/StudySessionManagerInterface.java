package model.manager;

import model.StudySession;

/** */
public interface StudySessionManagerInterface {

  /**
   * Creates a study sesssion and persists it to the database.
   *
   * @param studySession The study session to persist.
   */
  void create(final StudySession studySession);
}
