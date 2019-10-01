import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Module;
import java.io.IOException;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Min5aApplication extends GuiceApplication {
  @Inject private GuiceFXMLLoader fxmlLoader;

  @Inject private EventBus eventBus;

  @Override
  public void init(final List<Module> modules) throws Exception {
    modules.add(new DependencyInjectionModule());
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root =
        fxmlLoader
            .load(getClass().getClassLoader().getResource("fxml/FullPageTimer.fxml"))
            .getRoot();
    Scene scene = new Scene(root);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
