package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Course;
import model.PageFactory;

public class MainController extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader sidePanelLoader =
        new FXMLLoader(getClass().getClassLoader().getResource("fxml/SidePanel.fxml"));
    Parent root = sidePanelLoader.load(); // Loads the FXML for the SidePanel
    SidePanel sidePanelCtrl =
        sidePanelLoader.getController(); // Fetches the Controller for the fxml

    // Insert pages into side panel
    sidePanelCtrl.setHomePage(PageFactory.createHomePage());
    sidePanelCtrl.setCourseSelectionPage(PageFactory.createCourseSelectionPage());
    sidePanelCtrl.setStatisticsPage(PageFactory.createStatisticsPage());
    sidePanelCtrl.init();

    Scene scene = new Scene(root, 1200, 700);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
