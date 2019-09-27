package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SidePanel {

  @FXML private Button escapeHatchButton;

  @FXML private Button coursesPageButton;

  @FXML private Button statisticsPageButton;

  @FXML private AnchorPane mainPage;

  public void homePage(javafx.event.ActionEvent actionEvent) {
    mainPage.getChildren().clear();
    CourseOverviewPage courseOverviewPage = new CourseOverviewPage(this);
    mainPage.getChildren().add(courseOverviewPage);
    mainPage.toFront();
  }
}
