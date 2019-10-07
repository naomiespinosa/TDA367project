package viewcontroller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javax.swing.*;
import model.Course;
import model.ToDo;

public class CourseMainPage implements Initializable {

  private Course course;

  @FXML private Label courseName;

  // ToDoList
  @FXML private ListView<ToDo> toDoListView = new ListView<>();
  @FXML private Button newToDoButton;
  @FXML private Button removeToDoButton;
  @FXML private TextArea newToDo;

  // Deadline
  @FXML private ListView deadlineListView;
  @FXML private Button newDeadlineButton;
  @FXML private TextArea newDeadline;

  // Activity
  @FXML private ListView activityList;

  // Timer in Course
  @FXML private Button courseStartTimerButton;
  @FXML private TextArea courseTimer;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateToDoListView();
  }

  // Methods

  // TODO Here the course.getToDolist should be inputed instead of the random todos
  // Observable list for the courses To-Do's
  private ObservableList<ToDo> toDos = FXCollections.observableArrayList(new ToDo("mdkfkd"), new ToDo("jkdf"));

  // Adds a new to-do in the course-specific To-Do list
  // and puts the to-do into the ListView in the CourseMainPage
  @FXML
  private void addToDo(Event e) {
    if (newToDo.getText() != null) {
      toDos.add(new ToDo(newToDo.getText()));
      //course.getToDoList().add((new To-Do(newToDo.getText())));
      toDoListView.setItems(toDos);
      newToDo.setText(null);
    }
  }

  // Removes selected item in Listview and moves the selection up on step in the list
  @FXML
  private void removeToDo(Event e) {
    final int selectedIdx = toDoListView.getSelectionModel().getSelectedIndex();
    if (selectedIdx != -1) {
      ToDo itemToRemove = toDoListView.getSelectionModel().getSelectedItem();

      final int newSelectedIdx =
          (selectedIdx == toDoListView.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;

      toDoListView.getItems().remove(selectedIdx);
      toDoListView.getSelectionModel().select(newSelectedIdx);
    }
  }

  private void populateToDoListView(){
    toDoListView.setItems(toDos);
  }


  void init(Course course) {
    this.course = course;
    this.courseName.setText(course.getName());
  }
}
