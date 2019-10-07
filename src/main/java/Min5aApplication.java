import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Module;
import controller.SidePanel;
import java.io.IOException;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PageFactory;

public class Min5aApplication extends GuiceApplication {
  @Inject private GuiceFXMLLoader fxmlLoader;

  @Inject private EventBus eventBus;

  @Override
  public void init(final List<Module> modules) throws Exception {
    modules.add(new DependencyInjectionModule());
  }

  @Override
  public void start(Stage stage) throws IOException {
    final GuiceFXMLLoader.Result result = fxmlLoader
        .load(getClass().getClassLoader().getResource("fxml/SidePanel.fxml"));

    SidePanel sidePanelCtrl = result.getController(); // Fetches the Controller for the fxml

    // Insert pages into side panel
    sidePanelCtrl.setHomePage(PageFactory.createHomePage());
    sidePanelCtrl.setCourseSelectionPage(PageFactory.createCourseSelectionPage());
    sidePanelCtrl.setStatisticsPage(PageFactory.createStatisticsPage());
    sidePanelCtrl.init();

    final Parent root = result.getRoot();

    Scene scene = new Scene(root, 1200, 700);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
