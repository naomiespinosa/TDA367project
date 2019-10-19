package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.User;
import model.event.UserChangedEvent;
import model.manager.UserManagerInterface;
import model.repository.UserRepositoryInterface;

/**
 * This is the centre of the application and decides what page to be displayed and also handles the
 * login functions
 *
 * @author Hanna
 */
public class MainPage {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private AnchorPane main;
  @FXML private AnchorPane login;
  @FXML private Label loginErrorText;

  @Inject private UserRepositoryInterface userRepository;

  @Inject private PageFactory pageFactory;
  @Inject private EventBus eventBus;
  @Inject private UserManagerInterface userManager;

  /** This function initializes the page by deciding what to be displayed first */
  public void init() {
    showPage(homePage);
    login.toFront();
    main.toBack();
    resetLoginPage();
  }

  private void resetLoginPage() {
    loginErrorText.setText("");
    usernameTextField.clear();
  }

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

  // Displaying Pages functions
  /**
   * Shows selected page on the right side of the screen
   *
   * @param pane the AnchorPane to be shown
   */
  private void showPage(AnchorPane pane) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(pane);
    mainPage.toFront();
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

  void pressedCourseItem(Course course, final MainPage mainPage) throws IOException {
    AnchorPane courseHomePage = this.pageFactory.createCourseMainPage(course, mainPage);
    showPage(courseHomePage);
  }

  // Login Functionality

  @FXML
  private void login(ActionEvent event) {
    loginErrorText.setText("");
    List<User> users = userRepository.findAll();

    for (User user : users) {
      if (user.getUsername().equals(usernameTextField.getText())) {
        eventBus.post(new UserChangedEvent(user));
        login.toBack();
        main.toFront();
        resetLoginPage();
      } else {
        loginErrorText.setText("*Denna användaren finns inte");
      }
    }
  }

  @FXML
  private void newAccount() {
    loginErrorText.setText("");
    User user = new User();
    if (usernameIsValid()) {
      user.setUsername(usernameTextField.getText());

      userManager.create(user);
      eventBus.post(new UserChangedEvent(userRepository.findOneByUsername(user.getUsername())));

      login.toBack();
      main.toFront();
      resetLoginPage();
    } else {
      loginErrorText.setText("*Får inte lämnas tom");
    }
  }

  private boolean usernameIsValid() {
    return !usernameTextField.getText().trim().isEmpty();
  }
}
