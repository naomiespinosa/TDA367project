import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import model.timer.TimerManager;

public class DependencyInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(TimerManager.class).in(Singleton.class);
    bind(EventBus.class).in(Singleton.class);
  }
}
