package model.manager;

import com.google.inject.Inject;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

/** Manages user such as adding */
public class UserManager implements UserManagerInterface {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  public void create(User user) {
    this.fluentJdbc
        .query()
        .update("INSERT INTO users (username) VALUES (:username)")
        .namedParam("username", user.getUsername())
        .run();
  }
}
