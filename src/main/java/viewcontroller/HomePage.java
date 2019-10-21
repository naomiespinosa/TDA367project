package viewcontroller;

import com.google.common.eventbus.Subscribe;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.Min5a;
import model.event.CourseChangeEvent;
import model.event.UserChangedEvent;

public class HomePage implements Page {

  private MainPage parent;
  private Min5a model;

  @FXML private FlowPane activeCoursesFlowPane;

  @Subscribe
  private void onUserChange(UserChangedEvent userChangedEvent) {
    updateLists();
  }

  private void updateLists() {
    PanelItemManager.showCourses(activeCoursesFlowPane, parent, model.activeCourses());
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
}
