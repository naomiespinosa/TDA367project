package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CourseOverviewPage extends AnchorPane {
  private SidePanel sidePanel;

  public CourseOverviewPage(SidePanel sidePanel){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CourseOverviewPage.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    this.sidePanel = sidePanel;
    //set size 100%
    setBottomAnchor(this,0.0);
    setTopAnchor(this,0.0);
    setRightAnchor(this, 0.0);
    setLeftAnchor(this, 0.0);

  }


}
