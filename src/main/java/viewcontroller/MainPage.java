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
    homePage = PageLoader.createHomePage(this);
    courseSelectionPage = PageLoader.createCourseSelectionPage(this);
    statisticsPage = PageLoader.createStatisticsPage();
    timerPage = PageLoader.createTimerPage();
    contactsPage = PageLoader.createContactsPage();
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
    loginErrorText.setText("");
    String password = "42"; // TODO should come from GUI

    if (usernameIsValid()) {
      Integer personNumber = Integer.parseInt(usernameTextField.getText());
      if (model.login(personNumber, password)) {
        toHome();
      } else {
        loginErrorText.setText("*Denna användaren finns inte");
      }
    }
  }

  private boolean usernameIsValid() {
    if (usernameTextField.getText().trim().isEmpty()) {
      loginErrorText.setText("*Denna rutan får inte lämnas tom");
      return false;
    } else if (!usernameTextField.getText().matches("[0-9]+")) {
      loginErrorText.setText("*Endast siffror");
      return false;
    }
    return true;
  }

  @FXML
  private void newAccount() {
    loginErrorText.setText("");
    String name = usernameTextField.getText();
    String pwd = "tda367"; // TODO from GUI
    int personNumber = 42; // TODO should be supplied by GUI
    if (usernameIsValid()) {
      if (model.isUserUnique(name) && !usernameTextField.getText().trim().isEmpty()) {
        model.addUser(personNumber, name, pwd);
        model.login(personNumber, pwd);
        toHome();
      } else {
        loginErrorText.setText("*Användaren finns redan");
      }
    }
  }

  void toHome() {
    login.toBack();
    main.toFront();
    showPage(homePage);
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane pane) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(pane);
    mainPage.toFront();
  }
}
