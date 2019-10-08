package viewcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.min5aMainController;
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

public class StatisticsPage implements Initializable {

 /* @FXML private ListView activeCoursesListView;

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
*/

  @FXML
  private AnchorPane noGraphPane;

  @FXML
  private AnchorPane graphPane;

  @FXML
  private Button changeGraphPaneButton;

  @FXML
  private Text gradeUStudyTime;

  @FXML
  private Text grade3StudyTime;

  @FXML
  private Text grade4StudyTime;

  @FXML
  private Text grade5StudyTime;

  @FXML
  private Text totalStudyHour;

  @FXML
  private Text totalStudyMinute;

  @FXML
  private ListView<String> activeCoursesListView ;

  @FXML
  private ListView<String> finishedCoursesListView;

  private min5aMainController mainController = new min5aMainController();
  private boolean isGraphShowing = true;

  private ObservableList<String> items1 = FXCollections.observableArrayList();
  private ObservableList<String> items2 = FXCollections.observableArrayList();

 ArrayList<Course> activeCourseList;
 ArrayList<Course> finishedCourseList;


  /*@FXML
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
  }*/

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

 void setStudyTimeGradesDisplay(){
    //gradeUStudyTime.setText();
    //grade3StudyTime.setText();
    //grade4StudyTime.setText();
   // grade5StudyTime.setText();
    //TODO cannot yet connect because time sensitive information and functionality is not yet implemented.

  }

  void setTotalStudyTimeDisplay(){
      ArrayList<Course> courseList = mainController.getCourseList();
      int totalTimeSecond = 0;

      for(int i = 0; i < courseList.size();i++ ){
          Course course = courseList.get(i);
          for (StudySession studySession: course.getStudySessions()){
             totalTimeSecond += (int) studySession.getDuration().getSeconds();
          }
      }
      int totalHour = totalTimeSecond/3600;
      int totalMinute =(totalTimeSecond%3600)/60;

   //---------------------------------------------------
    totalStudyHour.setText(totalHour + " Timmar");
    totalStudyMinute.setText(totalMinute + " Minuter");
    //TODO access course total time info, the computing should be moved to another class perhaps in model. Leaving it here for now cus unsure where to put it.
  }

  void setListOfCourses(){
      ArrayList<Course> courseList =  mainController.getCourseList();

     for (Course course : courseList){
         if(course.isActive()) {
             items1.add(course.getName());
         }else{
             items2.add(course.getName());
         }
      }
      activeCoursesListView.setItems(items1);
      finishedCoursesListView.setItems(items2);


  }



}
