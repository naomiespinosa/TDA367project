import com.cathive.fx.guice.GuiceApplication;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Module;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewcontroller.PageLoader;

public class Min5aApplication extends GuiceApplication {
  @Inject private EventBus eventBus;
  @Inject private PageLoader pageLoader;

  @Override
  public void init(List<Module> modules) {
    modules.add(new DependencyInjectionModule());
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(pageLoader.createMainPage(), 1200, 700);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
