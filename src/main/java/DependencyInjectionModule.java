import com.google.inject.AbstractModule;
import model.User;
import viewcontroller.MainPage;

public class DependencyInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(User.class);
    bind(MainPage.class);
  }
}
