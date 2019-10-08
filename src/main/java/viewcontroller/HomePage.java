package viewcontroller;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;

public class HomePage {

  @FXML private FlowPane activeCoursesFlowpane;

  // Shared with CourseSelectionPage Will need to remake
  private void showActiveCourses(List<Course> courses, MainPage parent) throws IOException {
    activeCoursesFlowpane.getChildren().clear();
    for (Course course : courses) { // Runs through all the courses to only show the correct ones
      AnchorPane courseItem = PageFactory.createCoursePanelItem(course, parent);
      setShadow(courseItem);
      activeCoursesFlowpane.getChildren().add(courseItem);
    }
  }

  private void setShadow(
      AnchorPane courseItem) { // Make the CourseListItems to have a shadow around them
    DropShadow dropShadow = new DropShadow();
    dropShadow.setColor(Color.DARKGRAY);
    dropShadow.setOffsetX(3);
    dropShadow.setOffsetY(3);
    courseItem.setEffect(dropShadow);
  }
}
