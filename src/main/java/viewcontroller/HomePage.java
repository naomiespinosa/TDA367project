package viewcontroller;

import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.Min5a;
import model.Observer;
import model.event.UserChangedEvent;

import java.util.Optional;

public class HomePage implements Observer,Page {

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

  @Override
  public void update() {
    updateLists();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    mainPage.ifPresent(page -> parent = page);
  }
}
