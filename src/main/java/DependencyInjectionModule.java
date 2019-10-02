import com.google.inject.AbstractModule;
import controller.SidePanel;
import model.User;

public class DependencyInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(User.class);
    bind(SidePanel.class);
  }
}
