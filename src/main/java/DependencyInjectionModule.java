import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import model.User;
import model.repository.CourseRepository;
import model.repository.CourseRepositoryInterface;
import model.repository.UserRepository;
import model.repository.UserRepositoryInterface;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;
import org.sqlite.SQLiteDataSource;

public class DependencyInjectionModule extends AbstractModule {
  private User currentUser;

  @Override
  protected void configure() {
    bind(EventBus.class).in(Singleton.class);
    bind(CourseRepositoryInterface.class).to(CourseRepository.class);
    bind(UserRepositoryInterface.class).to(UserRepository.class);
  }

  @Provides
  @Singleton
  public FluentJdbc provideFluent() {
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl("jdbc:sqlite:database.sqlite");

    return new FluentJdbcBuilder().connectionProvider(dataSource).build();
  }

  @Provides
  @Singleton
  public ObjectMappers provideFluentObjectMappers() {
    return ObjectMappers.builder().build();
  }
}
