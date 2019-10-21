package viewcontroller;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;

class PanelItemManager {
  static void showCourses(FlowPane flowPane, MainPage parent, Iterable<Course> courses) {
    flowPane.getChildren().clear();

    for (Course course : courses) {
      AnchorPane courseItem = PageLoader.createCoursePanelItem(course, parent);
      setShadow(courseItem);
      flowPane.getChildren().add(courseItem);
    }
  }

  private static void setShadow(
      AnchorPane courseItem) { // Make the CourseListItems to have a shadow around them
    DropShadow dropShadow = new DropShadow();
    dropShadow.setColor(Color.DARKGRAY);
    dropShadow.setOffsetX(3);
    dropShadow.setOffsetY(3);
    courseItem.setEffect(dropShadow);
  }
}
