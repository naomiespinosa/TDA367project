package viewcontroller;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;

/**
 * This is a manager for creating the CoursePanelItems. It fills the flowPanes in
 * CourseSelectionPage and HomePage
 *
 * @author Hanna
 */
class PanelItemManager {
  static void showCourses(FlowPane flowPane, MainPage parent, Iterable<Course> courses) {
    flowPane.getChildren().clear();

    for (Course course : courses) {
      AnchorPane courseItem = PageLoader.createCoursePanelItem(course, parent);
      setShadow(courseItem);
      flowPane.getChildren().add(courseItem);
    }
  }

  private static void setShadow(AnchorPane courseItem) {
    DropShadow dropShadow = new DropShadow();
    dropShadow.setColor(Color.DARKGRAY);
    dropShadow.setOffsetX(3);
    dropShadow.setOffsetY(3);
    courseItem.setEffect(dropShadow);
  }
}
