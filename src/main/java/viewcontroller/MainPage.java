package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.User;
import model.event.UserChangedEvent;
import model.manager.UserManager;
import model.repository.UserRepository;

public class MainPage {

  @FXML private AnchorPane mainPage;
  @FXML private TextField usernameTextField;
  @FXML private AnchorPane main;
  @FXML private AnchorPane login;

  @Inject private UserRepository userRepository;

  @Inject private PageFactory pageFactory;
  @Inject private EventBus eventBus;
  @Inject private UserManager userManager;

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
  };

  void pressedCourseItem(Course course, final MainPage mainPage) throws IOException {
    AnchorPane courseHomePage = this.pageFactory.createCourseMainPage(course, mainPage);
    showPage(courseHomePage);
  }

  @FXML
  private void login(ActionEvent event) {
    List<User> users = this.userRepository.findAll();

    for (User user : users) {
      if (user.getUsername().equals(this.usernameTextField.getText())) {
        this.eventBus.post(new UserChangedEvent(user));
        this.login.toBack();
        main.toFront();
        init();
      }
    }
  }

  @FXML
  private void newAccount() {
    User user = new User();
    user.setUsername(usernameTextField.getText());

    this.userManager.create(user);
    this.eventBus.post(
        new UserChangedEvent(this.userRepository.findOneByUsername(user.getUsername())));

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
