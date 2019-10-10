package viewcontroller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.User;

public class MainPage {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private AnchorPane main;
  @FXML private AnchorPane login;

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

  // LoginPage

  @FXML
  void login(ActionEvent event) {
    // TODO login already existing user
  }

  @FXML
  void newAccount() {
    UserManger.setActiveUser(new User(usernameTextField.getText()));
    login.toBack();
    main.toFront();
    init();
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane page) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(page);
    mainPage.toFront();
  }
}
