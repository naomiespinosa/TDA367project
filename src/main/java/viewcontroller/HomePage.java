package viewcontroller;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.CourseManager;

public class HomePage implements Observer {
  private MainPage parent;

  @Inject private PanelItemManager panelItemManager;

  @FXML private FlowPane activeCoursesFlowpane;
  @Inject private CourseManager courseManager;

  void init() {
    // updateLists();
    this.courseManager.attach(this);
  }

  private void updateLists() {
  //  try {
  //    this.panelItemManager.showActiveCourses(activeCoursesFlowpane, parent, user);
  //  } catch (IOException e) {
 //     e.printStackTrace();
 //   }
  }

  void setParent(MainPage parent) {
    this.parent = parent;
  }

  @Override
  public void update() {
    // updateLists();
  }
}
