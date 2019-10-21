package model.repository;

import java.util.List;
import model.User;

/** Searches the database for users. */
public interface UserRepositoryInterface {
  /**
   * Finds all users.
   *
   * @return All users.
   */
  List<User> findAll();

  /**
   * Finds user by username.
   *
   * @param username Username to search for.
   * @return The matches user, if any. Otherwise null.
   */
  User findOneByUsername(final String username);
}
