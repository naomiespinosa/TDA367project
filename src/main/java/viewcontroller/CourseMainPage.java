package viewcontroller;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
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
import model.Moment;
import model.ToDo;

public class CourseMainPage implements Initializable {

  private Course course;

  // Observable list for the courses To-Do's
  private ObservableList<ToDo> toDos = FXCollections.observableArrayList();

  // Observable list for the courses Moments
  private ObservableList<Moment> moments = FXCollections.observableArrayList();

  // Observable list for the courses latest activity
  private ObservableList activities = FXCollections.observableArrayList();

  @FXML private Label courseName;
  @FXML private Label gradeLabel;

  // Status
  @FXML private Button toChangeStatusPageButton;
  @FXML private ComboBox gradeComboBox;
  @FXML private Label gradeText;
  @FXML private CheckBox termCheckBox;
  @FXML private Button changeStatusButton;
  @FXML private Label yearLabel;
  @FXML private Label studyPeriodLabel;

  // ToDoList
  @FXML private ListView<ToDo> toDoListView = new ListView<ToDo>();
  @FXML private Button newToDoButton;
  @FXML private Button removeToDoButton;
  @FXML private TextArea toDoTextArea;

  // Deadline
  @FXML private ListView<Moment> momentListView = new ListView<Moment>();
  @FXML private Button addMomentButton;
  @FXML private TextArea momentTextArea;
  @FXML private Button removeMomentButton;
  @FXML private DatePicker momentDatePicker;

  // Activity
  @FXML private ListView activityList = new ListView();

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
    populateListViews();
    momentDatePicker.setValue(LocalDate.now());
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

  // To-Do methods

  // Adds a new to-do in the course-specific To-Do list
  // and puts the to-do into the ListView in the CourseMainPage
  @FXML
  private void addToDo(Event e) {
    if (toDoTextArea.getText() != null) {
      ToDo todo1 = new ToDo(toDoTextArea.getText());
      toDos.add(todo1);
      course.newTodo(todo1.toString());
      toDoTextArea.setText(null);
    }
  }

  // Removes selected To-Do item in Listview and the courses To-Do list. Moves the selection up one
  // step in the list
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
  // Moment methods

  // Adds a Moment to the DeadlineListView as well as to the courses Moment list
  @FXML
  private void addMoment(Event event) {
    if (momentTextArea.getText() != null && momentDatePicker.getValue() != null) {
      Moment moment = new Moment(momentTextArea.getText(), momentDatePicker.getValue());

      moments.add(moment);
      course.newMoment(moment.toString(), momentDatePicker.getValue());

      momentTextArea.setText(null);
      momentDatePicker.setValue(LocalDate.now());

      moments.sort(moment.byDate);
      course.getMomentItems().sort(moment.byDate);

      System.out.println(course.getMomentItems());
    }
  }

  // Removes selected Moment item in Listview and the courses Moment list. Moves the selection up
  // one step in the list
  @FXML
  private void removeMoment() {
    final int selectedIdx = momentListView.getSelectionModel().getSelectedIndex();
    if (selectedIdx != -1) {
      Moment itemToRemove = momentListView.getSelectionModel().getSelectedItem();

      final int newSelectedIdx =
          (selectedIdx == momentListView.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;

      momentListView.getItems().remove(selectedIdx);
      course.deleteMoment(selectedIdx);

      course.getMomentItems().sort(itemToRemove.byDate);
      moments.sort(itemToRemove.byDate);

      momentListView.getSelectionModel().select(newSelectedIdx);
    }
  }

  // Latest Activity methods
  // TODO ask: what should latest actiivity show?

  /* TODO: add logic so when a todo is done or a moment has passed it's
  * deadline that it moves to the latest acticvity listview
  */

  // Populates the ListViews on the page with the correct items.
  private void populateListViews() {
    toDoListView.setItems(toDos);
    momentListView.setItems(moments);
  }

  // Populates the page with the correct course information
  void init(Course course) {
    this.course = course;
    this.courseName.setText(course.getName() + " " + course.getCourseCode());
    this.yearLabel.setText("Läsår:" + " " + course.getYear());
    this.studyPeriodLabel.setText("Läsperiod: " + " " + course.getStudyPeriod());
    this.toDos.addAll(course.getToDoList());
    this.moments.addAll(course.getMomentItems());
  // Setters And Getters
  void setParent(MainPage parent) {
    this.parent = parent;
  }

  public void startTimer() {
    this.parent.showTimerPage();
  }
}
