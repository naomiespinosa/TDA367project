import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import model.User;
import model.timer.TimerManager;
import viewcontroller.MainPage;

public class DependencyInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(TimerManager.class).in(Singleton.class);
    bind(EventBus.class).in(Singleton.class);
    bind(User.class);
    bind(MainPage.class);
  }
}
