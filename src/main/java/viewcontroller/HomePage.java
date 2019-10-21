package viewcontroller;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.Min5a;
import model.Observer;
import model.event.UserChangedEvent;

public class HomePage implements Observer {
  private MainPage parent;
  private Min5a model;

  @Inject private PanelItemManager panelItemManager;
  @FXML private FlowPane activeCoursesFlowPane;

  @Inject
  public HomePage(Min5a model) {
    this.model = model;
    model.register(this);
  }

  public void init() {
//    this.courseManager.attach(this);
  }

  @Subscribe
  private void onUserChange(UserChangedEvent userChangedEvent) {
    updateLists();
  }

  private void updateLists() {
    panelItemManager.showCourses(activeCoursesFlowPane, parent, model.activeCourses());
  }

  public void setParent(MainPage parent) {
    this.parent = parent;
  }

  @Override
  public void update() {
    updateLists();
  }
}
