package model.manager;

import model.User;

/** Manages users. */
public interface UserManagerInterface {

  /**
   * Creates a user and persists it to the database.
   *
   * @param user The user to be saved.
   */
  void create(User user);
}
