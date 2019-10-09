package viewcontroller;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javax.swing.*;
import model.Course;
import model.ToDo;

public class CourseMainPage implements Initializable {

  private Course course;

  @FXML private Label courseName;
  @FXML private Label gradeLabel;

  // Status
  @FXML private Button toChangeStatusPageButton;
  @FXML private ComboBox gradeComboBox;
  @FXML private Label gradeText;
  @FXML private CheckBox termCheckBox;
  @FXML private Button changeStatusButton;

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

  // Edit Course
  @FXML private TextField courseNameTextArea;
  @FXML private TextField courseCodeTextArea;
  @FXML private ComboBox periodComboBox;
  @FXML private Spinner yearSpinner;

  // Delete Course
  @FXML private Label deleteCourseText;

  // AnchorPanes
  @FXML private AnchorPane deleteCoursePane;
  @FXML private AnchorPane courseEditPane;
  @FXML private AnchorPane mainPane;
  @FXML private AnchorPane completeCoursePane;

  private MainPage parent;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateToDoListView();
    gradeComboBox.getItems().addAll(Course.getAcceptedGrades());
    this.courseStartTimerButton.setOnAction(
        e -> {
          startTimer();
        });
  }

  // manual initialize
  void init(Course course) {
    this.course = course;
    updateCourseInfo();
    resetPanes();
    deleteCourseText.setText(
        "Är du säker på att du vill radera kursen " + course.getCourseCode() + "?");
  }

  private void resetPanes() {
    mainPane.toFront();
    courseEditPane.toBack();
    deleteCoursePane.toBack();
    completeCoursePane.toBack();
  }

  private void updateCourseInfo() {
    this.courseName.setText(course.getName());
    changeStatusButton.setDisable(true);
    initStatusPane();
    initEditPane();
  }

  private void initEditPane() {
    periodComboBox.getItems().clear();

    periodComboBox.getItems().addAll(1, 2, 3, 4);

    SpinnerValueFactory<Integer> valueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, course.getYear(), 1);
    yearSpinner.setValueFactory(valueFactory);

    resetEditPane();
  }

  private void initStatusPane() {
    if (course.isActive()) {
      toChangeStatusPageButton.setText("Avsluta Kurs");
      changeStatusButton.setText("Avsluta Kurs");
      gradeComboBox.setVisible(true);
      gradeComboBox.getSelectionModel().select("3");
      gradeText.setVisible(true);
      termCheckBox.setText("Jag är säker på att jag vill avsluta kursen " + course.getName());
      gradeLabel.setText(null);

    } else {
      toChangeStatusPageButton.setText("Starta Kurs");
      changeStatusButton.setText("Starta Kurs");
      gradeComboBox.setVisible(false);
      gradeText.setVisible(false);
      termCheckBox.setText("Jag är säker på att jag vill återstarta kursen " + course.getName());
      gradeLabel.setText(course.getGrade());
    }
  }
  // Methods

  // TODO Here the course.getToDolist should be inputed instead of the random todos
  // Observable list for the courses To-Do's
  private ObservableList<ToDo> toDos =
      FXCollections.observableArrayList(new ToDo("mdkfkd"), new ToDo("jkdf"));

  // Adds a new to-do in the course-specific To-Do list
  // and puts the to-do into the ListView in the CourseMainPage
  @FXML
  private void addToDo(Event e) {
    if (newToDo.getText() != null) {
      toDos.add(new ToDo(newToDo.getText()));
      // course.getToDoList().add((new To-Do(newToDo.getText())));
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

  // Edit
  @FXML
  void editCourseTab(ActionEvent event) {
    resetPanes();
    courseEditPane.toFront();
    resetEditPane();
  }

  private void resetEditPane() {
    courseNameTextArea.setText(course.getName());
    courseCodeTextArea.setText(course.getCourseCode());

    periodComboBox.getSelectionModel().select((Object) course.getStudyPeriod());
    yearSpinner.getValueFactory().setValue(course.getYear());
  }

  @FXML
  void closeTabButton(ActionEvent event) {
    resetPanes();
    resetEditPane();
  }

  @FXML
  void closeTabMouse(MouseEvent event) {
    resetPanes();
    resetEditPane();
  }

  @FXML
  void changeCourse(ActionEvent event) {
    if (!isEditApproved()) {
      CourseManager.changeName(course, courseNameTextArea.getText());
      CourseManager.changeCode(course, courseCodeTextArea.getText());
      CourseManager.changeYear(course, (int) yearSpinner.getValue());
      CourseManager.changePeriod(
          course, (int) periodComboBox.getSelectionModel().getSelectedItem());

      updateCourseInfo();
      resetPanes();
    } else {

    }
  }

  private boolean isEditApproved() {
    return courseNameTextArea.getText().trim().isEmpty()
        || courseCodeTextArea.getText().trim().isEmpty();
  }

  // Delete Course
  @FXML
  void toDeleteCourse(ActionEvent event) {
    deleteCoursePane.toFront();
  }

  @FXML
  void deleteCourse(ActionEvent event) {
    CourseManager.deleteCourse(course);
    parent.init();
  }

  @FXML
  void cancelDelete(ActionEvent event) {
    deleteCoursePane.toBack();
  }

  // Complete Course

  @FXML
  void toChangeStatusPage(ActionEvent event) {
    resetPanes();
    resetChangeStatusPane();
    completeCoursePane.toFront();
  }

  @FXML
  void cancelChangeStatus() {
    resetPanes();
  }

  @FXML
  void termsAccepted() {
    if (termCheckBox.isSelected()) {
      changeStatusButton.setDisable(false);
    } else {
      changeStatusButton.setDisable(true);
    }
  }

  @FXML
  void changeStatus() {
    if (course.isActive()) {
      CourseManager.completeCourse(
          course, gradeComboBox.getSelectionModel().getSelectedItem().toString());
    } else {
      CourseManager.activateCourse(course);
    }
    resetPanes();
    updateCourseInfo();
    resetChangeStatusPane();
  }

  private void resetChangeStatusPane() {
    termCheckBox.setSelected(false);
    changeStatusButton.setDisable(true);
  }

  // TodoList-items
  private void populateToDoListView() {
    toDoListView.setItems(toDos);
  }

  // Setters And Getters
  void setParent(MainPage parent) {
    this.parent = parent;
  }

  public void startTimer() {
    this.parent.showTimerPage();
  }
}
