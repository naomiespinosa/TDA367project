package viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Course;
import model.Min5a;

import java.util.Optional;

public class CoursePanelItem implements Page {

  @FXML private Label courseName;
  @FXML private Label courseCode;

  // TODO model.manager.CourseManager & CourseFactory?
  private Course course;
  private MainPage parentController;


  @FXML
  private void goToPage(MouseEvent event) {
    parentController.pressedCourseItem(course);
  }

  void setCourse(Course course){
    this.course = course;
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.courseName.setText(course.getName());
    this.courseCode.setText(course.getCourseCode());
    mainPage.ifPresent(page -> parentController = page);
  }
}
