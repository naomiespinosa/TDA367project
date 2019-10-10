package viewcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.Course;

public class MainPage implements Initializable {

  @FXML private AnchorPane mainPage;

  // Other FXMLs
  private AnchorPane homePage;
  private AnchorPane courseSelectionPage;
  private AnchorPane statisticsPage;
  private AnchorPane timerPage;

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

  public void setTimerPage(AnchorPane timerPage) {
    this.timerPage = timerPage;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void init() {
    showPage(homePage);
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

  void showTimerPage() {
    showPage(timerPage);
  };

  // TODO should not depend on each other? how else to solve this?
  void pressedCourseItem(Course course, final MainPage mainPage) throws IOException {
    AnchorPane courseHomePage = PageFactory.createCourseMainPage(course, mainPage);
    showPage(courseHomePage);
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane page) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(page);
    mainPage.toFront();
  }
}
