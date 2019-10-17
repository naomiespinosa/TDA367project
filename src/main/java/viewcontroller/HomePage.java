package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.Observer;
import model.User;
import model.event.UserChangedEvent;
import model.manager.CourseManagerInterface;
import model.repository.CourseRepositoryInterface;

public class HomePage implements Observer {
  private MainPage parent;

  @Inject private PanelItemManager panelItemManager;

  @FXML private FlowPane activeCoursesFlowpane;
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
    this.user = userChangedEvent.getNewUser();
    this.updateLists();
  }

  private void updateLists() {
    try {
      this.panelItemManager.showActiveCourses(activeCoursesFlowpane, parent, user.getCourses());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void setParent(MainPage parent) {
    this.parent = parent;
  }

  @Override
  public void update() {
    updateLists();
  }
}
