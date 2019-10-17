package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;
import model.Observer;
import model.StudySession;
import model.User;
import model.event.UserChangedEvent;
import model.manager.CourseManagerInterface;
import model.repository.CourseRepositoryInterface;
import model.repository.StudySessionRepositoryInterface;

public class StatisticsPage implements Initializable, Observer {

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

  ArrayList<Course> activeCourseList;
  ArrayList<Course> finishedCourseList;

  @Inject private CourseManagerInterface courseManager;

  @Inject private CourseRepositoryInterface courseRepository;

  @Inject private StudySessionRepositoryInterface studySessionRepository;

  private User user;

  @Inject
  public StatisticsPage(final EventBus eventBus) {
    eventBus.register(this);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.courseManager.attach(this);
    addStudyTimePane.toBack();
    startPagePane.toFront();
  }

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
    for (Course course : user.getCourses()) {
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
    List<Course> courseList = user.getCourses();

    int totalTimeSecond = 0;

    List<StudySession> studySessions = this.studySessionRepository.findByUser(this.user);
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

    for (StudySession studySession : this.studySessionRepository.findByCourse(course)) {
      totalTimeSecond += (int) studySession.getDuration().getSeconds();
    }

    int totalHour = totalTimeSecond / 3600;
    return totalHour;

    // TODO adapt for Hour AND minutes, perhaps send back a Pair. This methods computing should also
    // be done in Course.
  }

  private void setListOfCourses() {
    List<Course> courseList = user.getCourses();

    activeCourses.clear();
    inactiveCourses.clear();

    for (Course course : courseList) {
      if (course.isActive()) {
        activeCourses.add(course.getName());
      } else {
        inactiveCourses.add(course.getName());
      }
    }
    activeCoursesListView.setItems(activeCourses);
    finishedCoursesListView.setItems(inactiveCourses);
  }

  @Override
  public void update() {
    setStudyTimeGradesDisplay();
    setTotalStudyTimeDisplay();
    setListOfCourses();
  }

  @Subscribe
  private void updateLists(final UserChangedEvent userChangedEvent) {
    this.user = userChangedEvent.getNewUser();
    this.update();
  }
}
