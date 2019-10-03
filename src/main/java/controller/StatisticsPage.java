package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;

import java.util.ArrayList;

public class StatisticsPage {

  @FXML
  private ListView<?> activeCoursesListView;

  @FXML
  private ListView<?> finishedCourseListView;

  @FXML
  private AnchorPane statisticsTimeListPane;

  @FXML
  private AnchorPane statisticsTimeBarhraphPane;

  @FXML
  private Button switchGraphPaneButton;

  @FXML
  private Text totalHourText;

  @FXML
  private Text totalMinuteText;

  @FXML
  private Text gradeUTime;

  @FXML
  private Text grade3Time;

  @FXML
  private Text grade4Time;

  @FXML
  private Text grade5Time;

  @FXML
  private Button addCourseStudyTimeButton;

  private ArrayList<Course> courses;










}
