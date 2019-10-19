package model.repository;

import java.util.List;
import model.User;

public interface UserRepositoryInterface {
  List<User> findAll();

  User findOneByUsername(final String username);
}
