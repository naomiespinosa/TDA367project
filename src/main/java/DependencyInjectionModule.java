import com.google.inject.AbstractModule;
import model.User;

public class DependencyInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(User.class);
  }
}
