package viewcontroller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class HomePage implements Observer {
  private MainPage parent;

  @FXML private FlowPane activeCoursesFlowpane;

  void init() {
    updateLists();
    CourseManager.attach(this);
  }

  private void updateLists() {
    try {
      PanelItemManager.showActiveCourses(activeCoursesFlowpane, parent);
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
