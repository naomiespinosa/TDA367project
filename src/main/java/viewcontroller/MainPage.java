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

  // Other FXMLs
  private AnchorPane homePage;
  private AnchorPane courseSelectionPage;
  private AnchorPane statisticsPage;
  private AnchorPane timerPage;
  private AnchorPane contactsPage;
  private CourseSelectionPage courseSelectionCtrl;

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

  public void setContactsPage(AnchorPane contactsPage) {
    this.contactsPage = contactsPage;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void init() {
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
  void showtFullPageTimer(ActionEvent event) {
    showTimerPage();
  }

  void showTimerPage() {
    showPage(timerPage);
  };

  void pressedCourseItem(Course course, final MainPage mainPage) throws IOException {
    AnchorPane courseHomePage = this.pageFactory.createCourseMainPage(course, mainPage);
    showPage(courseHomePage);
  }

  void initLoginPage() {
    login.toFront();
    main.toBack();
    loginErrorText.setText("");
    usernameTextField.clear();
  }

  @FXML
  private void login(ActionEvent event) {
    loginErrorText.setText("");
    List<User> users = userRepository.findAll();

    for (User user : users) {
      if (user.getUsername().equals(usernameTextField.getText())) {
        eventBus.post(new UserChangedEvent(user));
        login.toBack();
        main.toFront();
        init();
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
      init();
    } else {
      loginErrorText.setText("*Får inte lämnas tom");
    }
  }

  private boolean usernameIsValid() {
    return !usernameTextField.getText().trim().isEmpty();
  }

  // Shows selected page on the right side of the screen
  private void showPage(AnchorPane pane) {
    mainPage.getChildren().clear();
    mainPage.getChildren().add(pane);
    mainPage.toFront();
  }
}
