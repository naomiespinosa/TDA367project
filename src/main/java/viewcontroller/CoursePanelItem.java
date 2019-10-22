package viewcontroller;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Course;
import model.Min5a;

/**
 * This is the small boxes displaying courses that you can press on that are being shown in
 * FlowPanes
 *
 * @author Hanna
 */
public class CoursePanelItem implements Page {

  @FXML private Label courseName;
  @FXML private Label courseCode;
  private Course course;
  private MainPage parentController;

  @FXML
  private void goToPage(MouseEvent event) {
    parentController.pressedCourseItem(course);
  }

  /**
   * Sets a course to the coursepanelItem
   * @param course
   */
 public void setCourse(Course course) {
    this.course = course;
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.courseName.setText(course.getName());
    this.courseCode.setText(course.getCourseCode());
    mainPage.ifPresent(page -> parentController = page);
  }
}
