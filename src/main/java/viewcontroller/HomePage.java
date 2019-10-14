package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import event.UserChangedEvent;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import manager.CourseManager;
import model.Course;
import model.User;
import repository.CourseRepository;

public class HomePage implements Observer {
  private MainPage parent;

  @Inject private PanelItemManager panelItemManager;

  @FXML private FlowPane activeCoursesFlowpane;
  @Inject private CourseManager courseManager;
  @Inject private CourseRepository courseRepository;
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
    List<Course> courses = this.courseRepository.findByUser(this.user);

    try {
      this.panelItemManager.showActiveCourses(activeCoursesFlowpane, parent, courses);
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
