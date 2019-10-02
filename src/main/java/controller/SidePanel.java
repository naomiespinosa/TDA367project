package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class SidePanel implements Initializable {

  @FXML private AnchorPane mainPage;

  // Other FXMLs
  private AnchorPane homePage;
  private AnchorPane courseSelectionPage;
  private AnchorPane statisticsPage;

  // Other FXML setters
  public void setHomePage(AnchorPane homePage) {
    this.homePage = homePage;
  }

  public void setCourseSelectionPage(AnchorPane courseSelectionPage) {
    this.courseSelectionPage = courseSelectionPage;
  }

  public void setStatisticsPage(AnchorPane statisticsPage) {
    this.statisticsPage = statisticsPage;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void init() {
    showPage(courseSelectionPage);
  }

  @FXML
  public void showHomePage(ActionEvent actionEvent) {
    showPage(homePage);
  }

  @FXML
  void showCourseSelectionPage(ActionEvent event) {
    showPage(courseSelectionPage);
  }

  @FXML
  void showStatisticPage(ActionEvent event) {
    showPage(statisticsPage);
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane page) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(page);
    mainPage.toFront();
  }
}
