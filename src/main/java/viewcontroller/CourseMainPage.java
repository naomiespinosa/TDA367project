package viewcontroller;

import java.awt.*;
import java.io.IOException;
import java.lang.management.MemoryManagerMXBean;
import java.net.URL;
import java.time.LocalDate;
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
import model.Moment;
import model.ToDo;

public class CourseMainPage implements Initializable {

  private Course course;

  // Observable list for the courses To-Do's
  private ObservableList<ToDo> toDos = FXCollections.observableArrayList();

  // Observable list for the courses Moments
  private ObservableList<Moment> moments = FXCollections.observableArrayList();

  @FXML private Label courseName;
  @FXML private Label yearLabel;
  @FXML private Label studyPeriodLabel;

  // ToDoList
  @FXML private ListView<ToDo> toDoListView = new ListView<>();
  @FXML private Button newToDoButton;
  @FXML private Button removeToDoButton;
  @FXML private TextArea toDoTextArea;

  // Deadline
  @FXML private ListView deadlineListView =  new ListView<>();
  @FXML private Button addDeadlineButton;
  @FXML private TextArea deadlineTextArea;
  @FXML private Button removeDeadlineButton;
  @FXML private DatePicker deadlineDatePicker;

  // Activity
  @FXML private ListView activityList;

  // Timer in Course
  @FXML private Button courseStartTimerButton;
  @FXML private TextArea courseTimer;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateListViews();
    deadlineDatePicker.setValue(LocalDate.now());
  }

  // Methods

  // To-Do methods

  // Adds a new to-do in the course-specific To-Do list
  // and puts the to-do into the ListView in the CourseMainPage
  @FXML
  private void addToDo(Event e) {
    if (toDoTextArea.getText() != null) {
      ToDo todo1 = new ToDo(toDoTextArea.getText());
      toDos.add(todo1);
      course.newTodo(todo1.toString());
      //toDoListView.setItems(toDos);
      toDoTextArea.setText(null);
    }
  }

  // Removes selected item in Listview and moves the selection up one step in the list
  @FXML
  private void removeToDo(Event e) {
    final int selectedIdx = toDoListView.getSelectionModel().getSelectedIndex();
    if (selectedIdx != -1) {
      ToDo itemToRemove = toDoListView.getSelectionModel().getSelectedItem();

      final int newSelectedIdx =
          (selectedIdx == toDoListView.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;

      toDoListView.getItems().remove(selectedIdx);
      course.deleteTodo(selectedIdx);
      toDoListView.getSelectionModel().select(newSelectedIdx);
    }
  }

  // Moment methods

  @FXML
  private void addDeadline(Event event) {
    if (deadlineTextArea.getText() != null && deadlineDatePicker.getValue() != null) {
      Moment moment = new Moment(deadlineTextArea.getText(), deadlineDatePicker.getValue());
      moments.add(moment);
      course.newMoment(moment.toString(), deadlineDatePicker.getValue());
      deadlineTextArea.setText(null);

    }

  }


  private void populateListViews(){
    toDoListView.setItems(toDos);
    deadlineListView.setItems(moments);
  }

  void init(Course course) {
    this.course = course;
    this.courseName.setText(course.getName() + " " + course.getCourseCode());
    this.yearLabel.setText("Läsår:" + " " + course.getYear());
    this.studyPeriodLabel.setText("Läsperiod: " + " " + course.getStudyPeriod());
    this.toDos.addAll(course.getToDoList());
  }

}
