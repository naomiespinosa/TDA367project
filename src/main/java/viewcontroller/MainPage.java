package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;
import model.event.UserChangedEvent;

public class MainPage {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private AnchorPane main;
  @FXML private AnchorPane login;

  @Inject private PageLoader pageLoader;
  @Inject private EventBus eventBus;
  @Inject private Min5a model;

  // Other FXMLs
  private AnchorPane homePage;
  private AnchorPane courseSelectionPage;
  private AnchorPane statisticsPage;
  private AnchorPane timerPage;

  public void init() {
    // Insert pages into side panel
    homePage = pageLoader.createHomePage(this);
    courseSelectionPage = pageLoader.createCourseSelectionPage(this);
    statisticsPage = pageLoader.createStatisticsPage();
    timerPage = pageLoader.createTimerPage();

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
    showPage(pageLoader.createCourseMainPage(course, this));
  }

  @FXML
  private void login(ActionEvent event) {
    Integer personNumber = Integer.parseInt(usernameTextField.getText()); // TODO check if it is int
    String password = "42"; // TODO should come from GUI

    if (model.login(personNumber, password)) {
      eventBus.post(new UserChangedEvent());
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
