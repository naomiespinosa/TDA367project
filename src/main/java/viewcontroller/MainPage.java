package viewcontroller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;

public class MainPage implements Page {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private AnchorPane main;
  @FXML private AnchorPane login;

  @FXML private Label loginErrorText;

  private Min5a model;

  // Other FXMLs
  private AnchorPane homePage;
  private AnchorPane courseSelectionPage;
  private AnchorPane statisticsPage;
  private AnchorPane timerPage;
  private AnchorPane contactsPage;

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
    contactsPage = PageLoader.createContactsPage();

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

  @FXML
  void showContactsPage(ActionEvent event) {
    showPage(contactsPage);
  }

  @FXML
  private void showTimerPage(ActionEvent event) {
    showTimerPage();
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
      loginErrorText.setText("*Denna användaren finns inte");
    }
  }

  @FXML
  private void newAccount() {
    String name = usernameTextField.getText();
    String pwd = "tda367"; // TODO from GUI
    int personNumber = 42; // TODO should be supplied by GUI

    if (model.isUserUnique(name) && !usernameTextField.getText().trim().isEmpty()) {
      model.addUser(personNumber, name, pwd);
      model.login(personNumber, pwd);
    } else {
      loginErrorText.setText("*Användaren finns redan");
    }

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
