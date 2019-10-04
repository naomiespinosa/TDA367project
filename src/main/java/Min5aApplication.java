import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import com.google.inject.Module;
import model.User;
import viewcontroller.MainPage;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewcontroller.PageFactory;
import viewcontroller.PanelItemManager;

public class Min5aApplication extends GuiceApplication {
  @Inject private GuiceFXMLLoader fxmlLoader;

  @Override
  public void init(final List<Module> modules) throws Exception {
    modules.add(new DependencyInjectionModule());
  }

  @Override
  public void start(Stage stage) throws IOException {
    // FOR TESTING ONLY
    User user = User.getInstance();
    user.testing();
    // DELETE ABOVE


    FXMLLoader sidePanelLoader =
        new FXMLLoader(getClass().getClassLoader().getResource("fxml/SidePanel.fxml"));
    Parent root = sidePanelLoader.load(); // Loads the FXML for the SidePanel
    MainPage mainPageCtrl =
        sidePanelLoader.getController(); // Fetches the Controller for the fxml

    // Insert pages into side panel
    mainPageCtrl.setHomePage(PageFactory.createHomePage(mainPageCtrl));
    mainPageCtrl.setCourseSelectionPage(PageFactory.createCourseSelectionPage(mainPageCtrl));
    mainPageCtrl.setStatisticsPage(PageFactory.createStatisticsPage());
    mainPageCtrl.init();

    Scene scene = new Scene(root, 1200, 700);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
