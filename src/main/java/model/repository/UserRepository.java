package model.repository;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.util.List;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

/** @author Robin Hallabro-Kokko <robin@hallabro.nu> */
public class UserRepository implements UserRepositoryInterface {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;
  @Inject private Injector injector;

  @Override
  public List<User> findAll() {
    List<User> users =
        this.fluentJdbc
            .query()
            .select("SELECT * FROM users")
            .listResult(this.objectMappers.forClass(User.class));

    for (User user : users) {
      injector.injectMembers(user);
    }

    return users;
  }

  @Override
  public User findOneByUsername(final String username) {
    User user =
        this.fluentJdbc
            .query()
            .select("SELECT * FROM users WHERE username = :username")
            .namedParam("username", username)
            .singleResult(this.objectMappers.forClass(User.class));

    injector.injectMembers(user);

    return user;
  }
}
