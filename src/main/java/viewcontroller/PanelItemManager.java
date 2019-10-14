package viewcontroller;

import com.google.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;
import viewcontroller.MainPage;
import viewcontroller.PageFactory;

public class PanelItemManager {

  @Inject private PageFactory pageFactory;

  // Method used to display all Courses
  public void showActiveCourses(FlowPane activeCoursesFlowpane, MainPage parent, final List<Course> courses)
      throws IOException {
    activeCoursesFlowpane.getChildren().clear();
    for (Course course :
        sortCourses(
            true, courses)) { // Runs through all the courses to only show the correct ones
      AnchorPane courseItem = this.pageFactory.createCoursePanelItem(course, parent);
      setShadow(courseItem);
      activeCoursesFlowpane.getChildren().add(courseItem);
    }
  }

  // Method that displays all inactive courses
  public void showInactiveCourses(
      FlowPane inactiveCoursesFlowpane, MainPage parent, final List<Course> courses) throws IOException {
    inactiveCoursesFlowpane.getChildren().clear();

    for (Course course :
        sortCourses(
            false, courses)) { // Runs through all the courses to only show the correct ones
      AnchorPane courseItem = this.pageFactory.createCoursePanelItem(course, parent);
      setShadow(courseItem);
      inactiveCoursesFlowpane.getChildren().add(courseItem);
    }
  }

  private List<Course> sortCourses(Boolean status, final List<Course> courses) {
    List<Course> tempCourses = new ArrayList<>();
    for (Course course : courses) {
      if (course.isActive() == status) {
        tempCourses.add(course);
      }
    }
    return tempCourses;
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
