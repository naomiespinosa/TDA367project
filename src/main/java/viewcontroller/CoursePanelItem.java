package viewcontroller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Course;

/**
 * This is the small boxes displaying courses that you can press on that are being shown in
 * FlowPanes
 *
 * @author Hanna
 */
public class CoursePanelItem {

  @FXML private Label courseName;
  @FXML private Label courseCode;

  private Course course;
  private MainPage parentController;

  /**
   * Initializes the item
   *
   * @param course The course that this item belongs to
   * @param parentController to be able to make changes in the parentcontroller
   */
  void init(Course course, MainPage parentController) {
    this.courseName.setText(course.getName());
    this.courseCode.setText(course.getCourseCode());
    this.course = course;
    this.parentController = parentController;
  }

  @FXML
  private void goToPage(MouseEvent event) throws IOException {
    parentController.pressedCourseItem(course, this.parentController);
  }
}
