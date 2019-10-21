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

public class HomePage implements Page {

  private MainPage parent;
  private Min5a model;

  @FXML private FlowPane activeCoursesFlowPane;
  @FXML private TextField userName;
  @FXML private TextField securityCode;
  @FXML private TextField password;
  @FXML private Label changeWorked;

  @Subscribe
  private void onUserChange(UserChangedEvent userChangedEvent) {
    updateLists();
  }

  private void updateLists() {
    PanelItemManager.showCourses(activeCoursesFlowPane, parent, model.activeCourses());
    userName.setText(model.getActiveUserName());
    securityCode.setText(String.valueOf(model.getActiveUserId()));
    password.setText("*******");
    changeWorked.setText("");
  }

  @Subscribe
  public void courseChangeMade(CourseChangeEvent event) {
    updateLists();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    mainPage.ifPresent(page -> parent = page);
  }

  public void changeUser(ActionEvent event) {}

  public void logOut(ActionEvent event) {}
}
