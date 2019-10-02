package controller;

import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import model.Course;


public class CourseMainPage {
  @FXML private TextArea CourseName;

  // ToDoList
  @FXML private ListView toDoList;
  @FXML private Button newToDoButton;
  @FXML private TextArea newToDo;

  // Deadline
  @FXML private ListView deadlines;
  @FXML private Button newDeadlineButton;
  @FXML private TextArea newDeadline;

  // Activity
  @FXML private ListView activityList;

  // Timer in Course
  @FXML private Button courseStartTimerButton;
  @FXML private TextArea courseTimer;

  private Course course;
  private CourseSelectionPage parentController;

  public void init(Course course, CourseSelectionPage courseSelectionPage) {
    this.course = course;
    this.parentController = courseSelectionPage;
  }
}
