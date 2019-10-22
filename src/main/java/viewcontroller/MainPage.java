package viewcontroller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;

/**
 * This is the centre of the application and decides what page to be displayed and also handles the
 * login functions
 *
 * @author Hanna
 */
public class MainPage implements Page {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private PasswordField passwordField;
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

  // TODO CANT CHANGE USER

  @FXML
  private void login(ActionEvent event) {
    loginErrorText.setText("");

    if (usernameIsValid() && passwordIsValid()) {
      Integer personNumber = Integer.parseInt(usernameTextField.getText());
      if (model.login(personNumber, passwordField.getText())) {
        toHome();
      } else {
        loginErrorText.setText("*Personnummer eller lösenord fel");
      }
    }
  }

  private boolean passwordIsValid() {
    if (passwordField.getText().trim().isEmpty()) {
      loginErrorText.setText("Fel lösenord");
      return false;
    }
    return true;
  }

  private boolean usernameIsValid() { // TODO user persistence
    if (usernameTextField.getText().trim().isEmpty()) {
      loginErrorText.setText("*Ingen ruta får lämnas tom");
      return false;
    } else if (!usernameTextField.getText().matches("[0-9]+")) {
      loginErrorText.setText("*Endast siffror");
      return false;
    }
    return true;
  }

  @FXML
  private void newAccount() { // TODO getting errors lel
    loginErrorText.setText("");
    String pwd = passwordField.getText();
    int personNumber =
        Integer.parseInt(usernameTextField.getText()); // TODO should be supplied by GUI
    if (usernameIsValid() && passwordIsValid()) {
      if (model.isUserUnique(personNumber) && !usernameTextField.getText().trim().isEmpty()) {
        model.addUser(personNumber, "Your Name", pwd);
        model.login(personNumber, pwd); // TODO Wont work if i have logged in once
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

  void toLoginPage() {
    login.toFront();
    main.toBack();
    loginErrorText.setText("");
    usernameTextField.clear();
    passwordField.clear();
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane pane) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(pane);
    mainPage.toFront();
  }
}
