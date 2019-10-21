package viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;

import java.util.Optional;

public class MainPage implements Page {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private AnchorPane main;
  @FXML private AnchorPane login;

  private Min5a model;

  // Other FXMLs
  private AnchorPane homePage;
  private AnchorPane courseSelectionPage;
  private AnchorPane statisticsPage;
  private AnchorPane timerPage;

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
  }

  public void init() {
    // Insert pages into side panel
    homePage = PageLoader.createHomePage(this);
    courseSelectionPage = PageLoader.createCourseSelectionPage(this);
    statisticsPage = PageLoader.createStatisticsPage();
    timerPage = PageLoader.createTimerPage();

    showPage(homePage);
  }

  @FXML
  private void showHomePage(ActionEvent actionEvent) {
    showPage(homePage);
  }

  @FXML
  private void showCourseSelectionPage(ActionEvent event) {
    showPage(courseSelectionPage);
  }

  @FXML
  private void showStatisticPage(ActionEvent event) {
    showPage(statisticsPage);
  }

  void showTimerPage() {
    showPage(timerPage);
  }

  void pressedCourseItem(Course course) {
    showPage(PageLoader.createCourseMainPage(course, this));
  }

  @FXML
  private void login(ActionEvent event) {
    Integer personNumber = Integer.parseInt(usernameTextField.getText()); // TODO check if it is int
    String password = "42"; // TODO should come from GUI

    if (model.login(personNumber, password)) {
      login.toBack();
      main.toFront();
      init();
    } else {
      // TODO give message 'could not login'
    }
  }

  @FXML
  private void newAccount() {
    String name = usernameTextField.getText();
    String pwd = "tda367"; // TODO from GUI
    int personNumber = 42; // TODO should be supplied by GUI

    model.addUser(personNumber, name, pwd);
    model.login(personNumber, pwd);

    login.toBack();
    main.toFront();
    init();
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane pane) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(pane);
    mainPage.toFront();
  }
}
