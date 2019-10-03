package controller;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;

public class StatisticsPage {

  @FXML private ListView activeCoursesListView;

  @FXML private ListView finishedCourseListView;

  @FXML private AnchorPane statisticsTimeListPane;

  @FXML private AnchorPane statisticsTimeBarGraphPane;

  @FXML private Button switchGraphPaneButton;

  @FXML private Text totalHourText;

  @FXML private Text totalMinuteText;

  @FXML private Text gradeUTime;

  @FXML private Text grade3Time;

  @FXML private Text grade4Time;

  @FXML private Text grade5Time;

  @FXML private Button addCourseStudyTimeButton;

  private ArrayList<Course> courses = new ArrayList<>();

  private Boolean isGraphShowing = true;

  @FXML
  void switchListGraphAction(ActionEvent event) {
    if (isGraphShowing) {
      statisticsTimeListPane.toFront();
      statisticsTimeBarGraphPane.toBack();
      switchGraphPaneButton.setText("Byt till stapeldiagram");
      System.out.println("byt till lista");
    } else {
      statisticsTimeBarGraphPane.toFront();
      statisticsTimeListPane.toBack();
      switchGraphPaneButton.setText("Byt till listvy");
      System.out.println("byt till graf");
    }
  }
}
