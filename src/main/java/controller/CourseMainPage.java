package controller;

import java.awt.*;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javax.swing.*;
import model.Course;
import model.ToDo;

public class CourseMainPage {

  private ToDoItemController currentToDo;

  @FXML private TextArea CourseName;

  // ToDoList
  @FXML private ListView<ToDo> toDoListView;
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
  }

  // Methods

  // Adds a new to-do in the course-specific To-Do list
  // and puts the to-do into the ListView in the CourseMainPage
  private ObservableList<ToDo> toDos = FXCollections.observableArrayList();

  @FXML
  private void addToDo(Event e) {
    toDos.add(new ToDo(newToDo.getText()));
    toDoListView.setItems(toDos);
    newToDo.setText(null);
  }

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

  private void addCourseToDo(TextArea textArea, Course course) {
    course.newTodo(textArea.getText());
  }
}
