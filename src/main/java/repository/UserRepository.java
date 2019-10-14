package repository;

import com.google.inject.Inject;
import java.util.List;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

public class UserRepository {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  public List<User> findAll() {
    return this.fluentJdbc
        .query()
        .select("SELECT * FROM users")
        .listResult(this.objectMappers.forClass(User.class));
  }
}
