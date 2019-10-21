package model.manager;

import model.StudySession;

public interface StudySessionManagerInterface {

  /**
   * Creates a study sesssion and persists it to the database.
   *
   * @param studySession
   */
  void create(final StudySession studySession);
}
