package viewcontroller;

import java.time.LocalDate;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;
import model.Moment;
import model.ToDo;

/**
 * This class represents a specific page dedicated to the specific course. It shows info about the
 * course. It also allows the user to edit, complete and delete the course. The user can also add
 * and delete moments and to-dos
 *
 * @author Hanna
 * @author Theo
 */
public class CourseMainPage implements Page {

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
  private Min5a model;

  private void resetPanes() {
    mainPane.toFront();
    courseEditPane.toBack();
    deleteCoursePane.toBack();
    completeCoursePane.toBack();
  }

  private void updateCourseInfo() {
    this.courseName.setText(course.getName() + " " + course.getCourseCode());
    this.yearLabel.setText("Läsår:" + " " + course.getYear());
    this.studyPeriodLabel.setText("Läsperiod: " + " " + course.getStudyPeriod());
    this.toDos.clear();
    this.moments.clear();
    this.toDos.addAll(course.getToDoList());
    this.moments.addAll(course.getMomentItems());
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
  private void editCourseTab(ActionEvent event) {
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
  private void closeTabButton(ActionEvent event) {
    resetPanes();
    resetEditPane();
  }

  @FXML
  private void closeTabMouse(MouseEvent event) {
    resetPanes();
    resetEditPane();
  }

  @FXML
  private void changeCourse(ActionEvent event) {
    if (!isEditApproved()) {
      course.setName(courseNameTextArea.getText());
      course.setCourseCode(courseCodeTextArea.getText().substring(0, 6));
      course.setYear((int) yearSpinner.getValue());
      course.setStudyPeriod((int) periodComboBox.getSelectionModel().getSelectedItem());

      updateCourseInfo();
      resetPanes();
    }
  }

  private boolean isEditApproved() {
    return courseNameTextArea.getText().trim().isEmpty()
        || courseCodeTextArea.getText().trim().isEmpty();
  }

  // Makes it so the user only can type in a certain amount of characters in a textfield
  static void addTextLimiter(TextField courseCode, int limit) {
    courseCode
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  final ObservableValue<? extends String> ov,
                  final String oldValue,
                  final String newValue) {
                if (courseCode.getText().length() > limit) {
                  String s = courseCode.getText().substring(0, limit);
                  courseCode.setText(s);
                }
              }
            });
  }

  // Delete Course

  @FXML
  private void toDeleteCourse(ActionEvent event) {
    deleteCoursePane.toFront();
  }

  @FXML
  private void deleteCourse(ActionEvent event) {
    parent.toHome();
  }

  @FXML
  private void cancelDelete(ActionEvent event) {
    deleteCoursePane.toBack();
  }

  // Complete Course

  @FXML
  private void toChangeStatusPage(ActionEvent event) {
    resetPanes();
    resetChangeStatusPane();
    completeCoursePane.toFront();
  }

  @FXML
  private void cancelChangeStatus() {
    resetPanes();
  }

  @FXML
  private void termsAccepted() {
    if (termCheckBox.isSelected()) {
      changeStatusButton.setDisable(false);
    } else {
      changeStatusButton.setDisable(true);
    }
  }

  @FXML
  private void changeStatus() {
    if (course.isActive()) {
      course.endCourse(gradeComboBox.getSelectionModel().getSelectedItem().toString());
    } else {
      course.reactivateCourse();
    }

    resetPanes();
    updateCourseInfo();
    resetChangeStatusPane();
    model.notifyCourseChangedEvent();
  }

  private void resetChangeStatusPane() {
    termCheckBox.setSelected(false);
    changeStatusButton.setDisable(true);
  }

  // Moment methods

  // Adds a Moment to the DeadlineListView as well as to the courses Moment list
  @FXML
  private void addMoment(Event event) {
    if (momentTextArea.getText() != null && momentDatePicker.getValue() != null) {
      Moment moment = new Moment(momentTextArea.getText(), momentDatePicker.getValue());

      moments.add(moment);
      course.newMoment(momentTextArea.getText(), momentDatePicker.getValue());

      momentTextArea.setText(null);
      momentDatePicker.setValue(LocalDate.now());

      moments.sort(moment.byDate);
      course.getMomentItems().sort(moment.byDate);
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
  // TODO ask: what should latest activity show?

  /**
   * TODO: add logic so when a todo is done or a moment has passed it's deadline that it moves to
   * the latest acticvity listview
   */

  // Populates the ListViews on the page with the correct items.
  private void populateListViews() {
    toDoListView.setItems(toDos);
    momentListView.setItems(moments);
  }

  // Setters And Getters

  private void startTimer() {
    // this.eventBus.post(new CourseSelectedEvent(this.course));
    this.parent.showTimerPage();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    mainPage.ifPresent(page -> this.parent = page);
    updateCourseInfo();
    resetPanes();
    addTextLimiter(courseCodeTextArea, 6);
    deleteCourseText.setText(
        "Är du säker på att du vill radera kursen " + course.getCourseCode() + "?");
    populateListViews();
    momentDatePicker.setValue(LocalDate.now());
    populateListViews();
    gradeComboBox.getItems().addAll(Course.getAcceptedGrades());
    this.courseStartTimerButton.setOnAction(
        e -> {
          startTimer();
        });
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
