package viewcontroller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Course;

public class CoursePanelItem {

  @FXML private Label courseName;
  @FXML private Label courseCode;

  private Course course;
  private MainPage parentController;

  void init(Course course, MainPage parentController) {
    this.courseName.setText(course.getName());
    this.courseCode.setText(course.getCourseCode());
    this.parentController = parentController;
  }

  @FXML
  void goToPage(MouseEvent event) throws IOException {
    parentController.pressedCourseItem(course);
  }
}