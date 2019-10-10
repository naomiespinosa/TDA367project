package viewcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;
import model.StudySession;
import model.User;

public class StatisticsPage implements Initializable {

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

  private boolean isGraphShowing = true;

  private ObservableList<String> items1 = FXCollections.observableArrayList();
  private ObservableList<String> items2 = FXCollections.observableArrayList();

  ArrayList<Course> activeCourseList;
  ArrayList<Course> finishedCourseList;

  // Only for testing
  User user = User.getInstance();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setStudyTimeGradesDisplay();
    setTotalStudyTimeDisplay();
    setListOfCourses();
  }

  @FXML
  void switchListGraphAction(ActionEvent event) {
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

  void setStudyTimeGradesDisplay() {
    for (Course course : user.getCourses()) {
      if (!course.isActive()) {
        switch (course.getGrade()) {
          case "U":
            gradeUStudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
          case "3":
            gradeUStudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
          case "4":
            gradeUStudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
          case "5":
            gradeUStudyTime.setText(getTotalStudyTimeForCourse(course) + " Timme(ar)");
        }
      }
    }
    // TODO Fix so that Word HOUR is adapeted for 1, or several hours.

  }

  void setTotalStudyTimeDisplay() {
    ArrayList<Course> courseList = user.getCourses();
    int totalTimeSecond = 0;

    for (int i = 0; i < courseList.size(); i++) {
      Course course = courseList.get(i);
      for (StudySession studySession : course.getStudySessions()) {
        totalTimeSecond += (int) studySession.getDuration().getSeconds();
      }
    }
    int totalHour = totalTimeSecond / 3600;
    int totalMinute = (totalTimeSecond % 3600) / 60;

    // ---------------------------------------------------
    totalStudyHour.setText(totalHour + " Timmar");
    totalStudyMinute.setText(totalMinute + " Minuter");

    // TODO Computing in this method will later on be moved to Course and accessed via a method.
  }

  int getTotalStudyTimeForCourse(Course course) {
    int totalTimeSecond = 0;

    for (StudySession studySession : course.getStudySessions()) {
      totalTimeSecond += (int) studySession.getDuration().getSeconds();
    }

    int totalHour = totalTimeSecond / 3600;
    return totalHour;

    // TODO adapt for Hour AND minutes, perhaps send back a Pair. This methods computing should also
    // be done in Course.
  }

  void setListOfCourses() {
    ArrayList<Course> courseList = user.getCourses();

    for (Course course : courseList) {
      if (course.isActive()) {
        items1.add(course.getName());
      } else {
        items2.add(course.getName());
      }
    }
    activeCoursesListView.setItems(items1);
    finishedCoursesListView.setItems(items2);
  }
}
