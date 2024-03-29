package viewcontroller;

import com.google.common.eventbus.Subscribe;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import model.Min5a;
import model.event.CourseChangeEvent;
import model.event.UserChangedEvent;

/**
 * This is the frontpage that shows active courses and also info about the user
 *
 * @author Hanna
 */
public class HomePage implements Page {

  private MainPage parent;
  private Min5a model;

  @FXML private FlowPane activeCoursesFlowPane;
  @FXML private TextField userName;
  @FXML private Label worked;

  @Subscribe
  private void onUserChange(UserChangedEvent userChangedEvent) {
    updateLists();
  }

  private void updateLists() {

    PanelItemManager.showCourses(activeCoursesFlowPane, parent, model.activeCourses());
    userName.setText(model.getActiveUserName());
    worked.setText("");
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    mainPage.ifPresent(page -> parent = page);
  }

  @FXML
  void changeUser(ActionEvent event) {
    worked.setText("");
    if (isValidChangeMade()) {
      model.setActiveUserName(userName.getText());
      // model.setActiveUserId(Integer.parseInt(securityCode.getText()));
      worked.setText("Ändringarna är nu uppdaterade");
    }
  }

  private boolean isValidChangeMade() {
    return !isInputEmpty() && !isChangeMade();
  }

  private boolean isChangeMade() {
    return model.getActiveUserName().equals(userName.getText());
    // || Integer.valueOf(securityCode.getText()) == model.getActiveUserId();
  }

  private boolean isInputEmpty() {
    return userName.getText().trim().isEmpty();
    // || securityCode.getText().trim().isEmpty();
  }

  @FXML
  void logOut(ActionEvent event) {
    model.logout();
    parent.toLoginPage();
  }

  @Subscribe
  public void userUpdated(UserChangedEvent event) {
    updateLists();
  }

  @Subscribe
  public void courseChangeMade(CourseChangeEvent event) {
    updateLists();
  }
}
