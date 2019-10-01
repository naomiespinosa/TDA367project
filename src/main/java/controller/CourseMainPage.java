package controller;

import java.awt.*;
import java.io.IOException;
import java.util.Observable;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.Event;
import javafx.scene.layout.*;
import javax.swing.*;

import model.Course;
import model.ToDo;

public class CourseMainPage {

  private ToDoItemController currentToDo;

  @FXML
  private TextArea CourseName;

  // ToDoList
  @FXML
  private ListView<ToDo> toDoListView;
  @FXML
  private Button newToDoButton;
  @FXML
  private TextArea newToDo;

  // Deadline
  @FXML
  private ListView deadlineListView;
  @FXML
  private Button newDeadlineButton;
  @FXML
  private TextArea newDeadline;

  // Activity
  @FXML
  private ListView activityList;

  // Timer in Course
  @FXML
  private Button courseStartTimerButton;
  @FXML
  private TextArea courseTimer;

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

    newToDoButton.setOnAction(event -> System.out.println("clicked"));
  }

  // Methods


  // Adds a new to-do in the course-specific To-Do list
  // and puts the to-do into the ListView in the CourseMainPage
  ObservableList<ToDo> toDos = FXCollections.observableArrayList();
  @FXML
    private void addToDo(Event e) {
    toDos.add(new ToDo(newToDo.getText()));
    toDoListView.setItems(toDos);
    newToDo.setText(null);
  }

  private void addCourseToDo(TextArea textArea, Course course) {
    course.newTodo(textArea.getText());
  }

}



