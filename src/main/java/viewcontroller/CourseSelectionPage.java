package viewcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class CourseSelectionPage implements Initializable, Observer {

  @FXML private FlowPane activeCoursesFlowPane;
  @FXML private FlowPane inactiveCoursesFlowPane;
  @FXML private AnchorPane main;
  @FXML private AnchorPane addCoursePane;

  @FXML TextField courseNameTextArea;
  @FXML TextField courseCodeTextArea;
  @FXML private Spinner yearSpinner;
  private int initialYear = 2019;
  @FXML private ToggleGroup periodToggleGroup;
  @FXML private RadioButton period1RadioButton;

  @FXML private RadioButton period2RadioButton;

  @FXML private RadioButton period3RadioButton;

  @FXML private RadioButton period4RadioButton;

  private MainPage parent;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initToggleGroup();
    resetSpinner();
  }

  void init() {
    // Temporary
    resetPage();
    updateLists();
    addTextLimiter(courseCodeTextArea);
  }

  private void resetPage() {
    main.toFront();
    addCoursePane.toBack();
    CourseManager.attach(this);
  }

  private void updateLists() {
    try {
      PanelItemManager.showActiveCourses(activeCoursesFlowPane, parent);
      PanelItemManager.showInactiveCourses(inactiveCoursesFlowPane, parent);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void addTextLimiter(TextField courseCodeText) {
    courseCodeText
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  final ObservableValue<? extends String> ov,
                  final String oldValue,
                  final String newValue) {
                if (courseCodeText.getText().length() > 6) {
                  String s = courseCodeText.getText().substring(0, 6);
                  courseCodeText.setText(s);
                }
              }
            });
  }

  // Add course functionality
  @FXML
  private void addCourse(ActionEvent event) {
    main.toBack();
    addCoursePane.toFront();
  }

  @FXML
  private void closeTabButton(ActionEvent event) {
    resetPage();
  }

  @FXML
  private void closeTabMouse(MouseEvent event) {
    resetPage();
  }

  @FXML
  private void createNewCourse(ActionEvent event) {
    addCourse();
  }

  private void addCourse() {
    if (!isNewCourseApproved()) { // Check so all fields are filled in
      CourseManager.createNewCourse(
          courseNameTextArea.getText(),
          courseCodeTextArea
              .getText()
              .substring(0, 6), // Makes sure we do not allow more than 6 chars
          (int) yearSpinner.getValue(),
          getPeriod());

      clearCourseInput();
      resetPage();
    }
  }

  private boolean isNewCourseApproved() {
    return courseNameTextArea.getText().trim().isEmpty()
        || courseCodeTextArea.getText().trim().isEmpty();
  }

  @FXML
  private void deleteInputs(ActionEvent event) {
    clearCourseInput();
  }

  private void clearCourseInput() {
    courseNameTextArea.clear();
    courseCodeTextArea.clear();
    period1RadioButton.setSelected(true);
    resetSpinner();
  }

  // Spinner
  private void resetSpinner() {
    SpinnerValueFactory<Integer> valueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, initialYear, 1);
    yearSpinner.setValueFactory(valueFactory);
  }

  // Togglegroup

  private void initToggleGroup() {
    periodToggleGroup = new ToggleGroup();
    period1RadioButton.setToggleGroup(periodToggleGroup);
    period2RadioButton.setToggleGroup(periodToggleGroup);
    period3RadioButton.setToggleGroup(periodToggleGroup);
    period4RadioButton.setToggleGroup(periodToggleGroup);

    period1RadioButton.setSelected(true);
  }

  private int getPeriod() { // TODO
    if (periodToggleGroup.getSelectedToggle() != null) {
      if (periodToggleGroup.getSelectedToggle() == period1RadioButton) {
        return 1;
      } else if (periodToggleGroup.getSelectedToggle() == period2RadioButton) {
        return 2;
      } else if (periodToggleGroup.getSelectedToggle() == period3RadioButton) {
        return 3;
      } else if (periodToggleGroup.getSelectedToggle() == period1RadioButton) {
        return 4;
      }
    }
    return 0;
  }

  // Setters And Getters
  void setParent(MainPage parent) {
    this.parent = parent;
  }

  @Override
  public void update() {
    updateLists();
  }
}
