package controller;

import java.awt.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javax.swing.*;
import javax.swing.text.html.ListView;
import model.Course;

public class CourseMainPage extends AnchorPane {
  @FXML private TextArea CourseName;

  // ToDoList
  @FXML private FlowPane toDoListFlowPane;
  @FXML private Button newToDoButton;
  @FXML private TextArea newToDo;

  // Deadline
  @FXML private FlowPane deadlineListFlowPane;
  @FXML private Button newDeadlineButton;
  @FXML private TextArea newDeadline;

  // Activity
  @FXML private ListView activityList;

  // Timer in Course
  @FXML private Button courseStartTimerButton;
  @FXML private TextArea courseTimer;

  //  Constructor for controller
  public void CourseMainPage() {

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CourseMainPage.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    // TODO The specific course still needs to be specified
    // newDeadlineButton.setOnAction(event -> addToDo(newToDo, Course course));

  }

  // Functions

  // Adds a new to-do in the course-specific To-Do list
  // and puts it into the flowpane in the Course Main Page
  public void addToDo(TextArea textArea, Course course) {
    course.newTodo(textArea.getText());

    textArea.clear();
    // TODO add the new todo in the flowpane
  }

  /* Function for adding a ChangeListener to TextAreas
    public void addChangeListener(TextArea textArea) {
      textArea.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

        }
      });
    }
  */
}
