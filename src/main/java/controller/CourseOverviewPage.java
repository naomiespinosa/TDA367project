package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CourseOverviewPage extends AnchorPane {

  private SidePanel sidePanel;

  public CourseOverviewPage(SidePanel sidePanel) {
    FXMLLoader fxmlLoader = new FXMLLoader(CourseOverviewPage.class.getClassLoader().getResource("fxml/CourseOverviewPage.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);


    // TODO fix this stuff
    try {
      fxmlLoader.load();
    } catch (IOException exception){
      throw new RuntimeException();
    }

    this.sidePanel = sidePanel;
  }
}
