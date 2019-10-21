package viewcontroller;

import com.google.common.eventbus.Subscribe;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Min5a;
import model.event.CourseChangeEvent;

public class CourseSelectionPage implements Page {

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
  private Min5a model;

  private void resetPage() {
    main.toFront();
    addCoursePane.toBack();
  }

  private void updateLists() {
    resetPage();
    PanelItemManager.showCourses(activeCoursesFlowPane, parent, model.activeCourses());
    PanelItemManager.showCourses(inactiveCoursesFlowPane, parent, model.inActiveCourses());
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
      model.addCourse(
          courseNameTextArea.getText(),
          courseCodeTextArea.getText().substring(0, 6),
          (int) yearSpinner.getValue(),
          getPeriod());
      clearCourseInput();
      resetPage();
      updateLists();
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

  // ToggleGroup
  private void initToggleGroup() {
    periodToggleGroup = new ToggleGroup();
    period1RadioButton.setToggleGroup(periodToggleGroup);
    period2RadioButton.setToggleGroup(periodToggleGroup);
    period3RadioButton.setToggleGroup(periodToggleGroup);
    period4RadioButton.setToggleGroup(periodToggleGroup);

    period1RadioButton.setSelected(true);
  }

  private int getPeriod() {
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

  static void addTextLimiter(TextField courseCodeText, int limit) {
    courseCodeText
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  final ObservableValue<? extends String> ov,
                  final String oldValue,
                  final String newValue) {
                if (courseCodeText.getText().length() > limit) {
                  String s = courseCodeText.getText().substring(0, limit);
                  courseCodeText.setText(s);
                }
              }
            });
  }

  @Subscribe
  public void courseChaneMade(CourseChangeEvent event) {
    updateLists();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    mainPage.ifPresent(page -> parent = page);
    initToggleGroup();
    resetSpinner();
    addTextLimiter(courseCodeTextArea, 6); // Code Reuse
  }
}
