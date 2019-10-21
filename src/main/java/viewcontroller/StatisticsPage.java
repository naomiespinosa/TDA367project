package viewcontroller;

import com.google.common.eventbus.Subscribe;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;
import model.Min5a;
import model.StudySession;
import model.event.CourseChangeEvent;
import model.event.UserChangedEvent;

public class StatisticsPage implements Page {

  @FXML private AnchorPane noGraphPane;
  @FXML private AnchorPane graphPane;
  @FXML private Button changeGraphPaneButton;
  @FXML private Text gradeUStudyTime;
  @FXML private Text grade3StudyTime;
  @FXML private Text grade4StudyTime;
  @FXML private Text grade5StudyTime;
  @FXML private Text totalStudyHour;
  @FXML private Text totalStudyMinute;
  @FXML private ListView<String> activeCoursesListView;
  @FXML private ListView<String> finishedCoursesListView;
  @FXML private AnchorPane addStudyTimePane;
  @FXML private AnchorPane startPagePane;
  @FXML private ComboBox<?> chooseCourseComboBox;
  @FXML private Button saveStudyTimeButton;
  @FXML private ComboBox<?> chooseHoursComboBox;
  @FXML private ComboBox<?> chooseMinutesComboBox;

  private boolean isGraphShowing = true;

  private ObservableList<String> activeCourses = FXCollections.observableArrayList();
  private ObservableList<String> inactiveCourses = FXCollections.observableArrayList();
  private Min5a model;
  private Optional<MainPage> mainPage;

  //  List<Course> activeCourseList;
  //  List<Course> finishedCourseList;

  // TODO add functionality for add studyTime dialog

  @FXML
  private void switchListGraphAction(ActionEvent event) {
    if (isGraphShowing) {
      noGraphPane.toFront();
      changeGraphPaneButton.setText("Byt till stapeldiagram");
      isGraphShowing = false;
    } else {
      graphPane.toFront();
      changeGraphPaneButton.setText("Byt till listvy");
      isGraphShowing = true;
    }
  }

  @FXML
  void openAddStudyTimeDialogBox(ActionEvent event) {
    startPagePane.toBack();
    addStudyTimePane.toFront();
  }

  @FXML
  void cancelAddStudyTime(ActionEvent event) {
    addStudyTimePane.toBack();
    startPagePane.toFront();
  }

  private void setStudyTimeGradesDisplay() {
    Iterable<Course> courses = null;
    for (Course course : courses) {
      if (!course.isActive()) {
        switch (course.getGrade()) {
          case "U":
            gradeUStudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
            break;
          case "3":
            grade3StudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
            break;
          case "4":
            grade4StudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
            break;
          case "5":
            grade5StudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
            break;
          default:
        }
      }
    }
  }

  // TODO Computing in this method will later on be moved to Course and accessed via a method.
  // TODO no dependancy

  private void setTotalStudyTimeDisplay() {
    int totalTimeSecond = 0;

    List<StudySession> studySessions = null; // user.getStudySessions();
    for (StudySession studySession : studySessions) {
      totalTimeSecond += (int) studySession.getDuration().getSeconds();
    }

    int totalHour = totalTimeSecond / 3600;
    int totalMinute = (totalTimeSecond % 3600) / 60;

    // ---------------------------------------------------
    totalStudyHour.setText(totalHour + " Timmar");
    totalStudyMinute.setText(totalMinute + " Minuter");

    // TODO Computing in this method will later on be moved to Course and accessed via a method.
  }

  private int getTotalStudyTimeForCourse(Course course) {
    int totalTimeSecond = 0;

    Iterable<StudySession> studySessions = null; // find study sessions for a course
    for (StudySession studySession : studySessions) {
      totalTimeSecond += (int) studySession.getDuration().getSeconds();
    }

    int totalHour = totalTimeSecond / 3600;
    return totalHour;

    // TODO adapt for Hour AND minutes, perhaps send back a Pair. This methods computing should also
    // be done in Course.
  }

  private void setListOfCourses() {
    activeCourses.clear();
    inactiveCourses.clear();

    Iterable<Course> courses = null;

    for (Course course : courses) {
      if (course.isActive()) {
        activeCourses.add(course.getName());
      } else {
        inactiveCourses.add(course.getName());
      }
    }
    activeCoursesListView.setItems(activeCourses);
    finishedCoursesListView.setItems(inactiveCourses);
  }

  @Subscribe
  public void courseChange(CourseChangeEvent event) {
    update();
  }

  @Subscribe
  private void updateLists(final UserChangedEvent userChangedEvent) {
    update();
  }

  private void update() {
    setStudyTimeGradesDisplay();
    setTotalStudyTimeDisplay();
    setListOfCourses();
  }



  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    this.mainPage = mainPage;
    // update();
  }
}
