import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import model.Min5a;
import model.listener.TimerListener;

public class DependencyInjectionModule extends AbstractModule {

  @Override
  protected void configure() {
//    bind(EventBus.class).in(Singleton.class);
    bind(TimerListener.class).asEagerSingleton();
    bind(Min5a.class).in(Singleton.class);
  }
}
