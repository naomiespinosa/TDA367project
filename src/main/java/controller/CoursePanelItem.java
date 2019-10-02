package controller;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import model.Course;
import javafx.scene.control.Label;

import java.io.IOException;


public class CoursePanelItem {

  @FXML private Label courseName;

  @Inject private SidePanel sidePanel;

  private Course course;
  private CourseSelectionPage parentController;

  public void init(Course course, CourseSelectionPage parentController){
    this.courseName.setText(course.getName());
    this.parentController = parentController;
  }

  @FXML
  void goToPage(MouseEvent event) throws IOException {
    parentController.pressed(course);
  }

}
