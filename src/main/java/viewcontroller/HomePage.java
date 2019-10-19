package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import model.Observer;
import model.User;
import model.event.UserChangedEvent;
import model.manager.CourseManagerInterface;
import model.repository.CourseRepositoryInterface;

/**
 * This is the frontpage that shows active courses and also info about the user
 *
 * @author Hanna
 */
public class HomePage implements Observer {
  private MainPage parent;

  @Inject private PanelItemManager panelItemManager;

  @FXML private FlowPane activeCoursesFlowpane;
  @FXML private TextField userName;
  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private Label changeWorked;
  @Inject private CourseManagerInterface courseManager;
  @Inject private CourseRepositoryInterface courseRepository;
  private User user;

  @Inject
  public HomePage(final EventBus eventBus) {
    eventBus.register(this);
  }

  void init() {
    this.courseManager.attach(this);
  }

  @Subscribe
  private void onUserChange(final UserChangedEvent userChangedEvent) {
    user = userChangedEvent.getNewUser();
    updateLists();
  }

  void updateLists() {
    try {
      panelItemManager.showActiveCourses(activeCoursesFlowpane, parent, user.getCourses());
      initUserInfo();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initUserInfo() {
    userName.setText(user.getUsername());
    email.setText("myemail@mail.com");
    password.setText("*******");
    changeWorked.setText("");
  }

  void setParent(MainPage parent) {
    this.parent = parent;
  }

  // TODO insert in SQL
  @FXML
  void changeUser(ActionEvent event) {
    changeWorked.setText("");
    if (isValidChangeMade()) {
      user.setUsername(userName.getText());
      changeWorked.setText("Ändringarna är nu uppdaterade");
    }
  }

  private boolean isValidChangeMade() {
    return isInputEmpty() && isChangeMade();
  }

  private boolean isChangeMade() {
    return !user.getUsername().equals(userName.getText());
  }

  private boolean isInputEmpty() {
    return !userName.getText().trim().isEmpty();
  }

  @FXML
  void logOut(ActionEvent event) {
    parent.init();
  }

  @Override
  public void update() {
    updateLists();
  }
}
