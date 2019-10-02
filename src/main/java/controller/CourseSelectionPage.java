package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Course;
import model.PageFactory;
//import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseSelectionPage {
    private List<Course> activeCourses;
    private List<Course> inactiveCourses;
    private List<Course> allCourses;
    public CourseSelectionPage coursePanelItemCtrl;

    @FXML
    private FlowPane activeCoursesFlowpane;

    @FXML
    private FlowPane inactiveCoursesFlowpane;

    @FXML
    private AnchorPane smallCoursePanelItem;
/*    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i;
        for (i = 0; i <= allCourses.size() - 1; i++){

        }

    }*/

   /* public void showCourseItem() throws IOException {
        FXMLLoader courseSelectionLoader =
                new FXMLLoader(getClass().getClassLoader().getResource("fxml/CoursePanelItem.fxml"));Parent root2 = courseSelectionLoader.load(); // Loads the FXML for the Panel
        CourseSelectionPage courseSelectionCtrl =
                courseSelectionLoader.getController(); // Fetches the Controller for the fxml

        Course testCourse1 = new Course("testtes3t", "TEST1323",2019,4);

        Course testCourse = new Course("testtest", "TEST123",2019,4);
        CoursePanelItem item = new CoursePanelItem(testCourse, courseSelectionCtrl);
        CoursePanelItem item2 = new CoursePanelItem(testCourse1, courseSelectionCtrl);
       // activeCoursesFlowpane.getChildren().add(item);
       // activeCoursesFlowpane.getChildren().add(item2);


    }*/

    private void SortCourses(List<Course> activeCourses, List<Course> inactiveCourses, List<Course> allCourses) {
        int i;
        for (i = 0; i <= allCourses.size() - 1; i++) {
            if (allCourses.get(i).isActive()) {
                activeCourses.add(allCourses.get(i));
            } else inactiveCourses.add(allCourses.get(i));
        }
    }

    public void setActiveCoursePanelItem(FlowPane activeCoursesFlowpane) {
        this.activeCoursesFlowpane = activeCoursesFlowpane;
    }

    public void setinactiveCoursePanelItem(FlowPane inactiveCoursesFlowpane) {
        this.inactiveCoursesFlowpane = inactiveCoursesFlowpane;
    }
    public void setCoursePanelItem(AnchorPane smallCoursePanelItem )
    { this.smallCoursePanelItem = smallCoursePanelItem; }


   public void updateSelectedCourses(List<Course> activeCourses) throws IOException {
       activeCoursesFlowpane.getChildren().clear();
       int i;
       for (i = 0; i <= activeCourses.size() - 1; i++){
        FXMLLoader coursePanelItemLoader =
               new FXMLLoader(getClass().getClassLoader().getResource("fxml/CoursePanelItem.fxml"));
       Parent root = coursePanelItemLoader.load();
       CourseSelectionPage coursePanelItemCtrl = coursePanelItemLoader.getController();
           setCoursePanelItem(PageFactory.createCoursePanelItem());
             // activeCoursesFlowpane.getChildren().add(activeCourses.get(i));

           }
       }




    /*
     @FXML
    void showCourseSelectionPage(ActionEvent event) {
        showPage(CourseMainPage);
    }

    private void showPage(FlowPane page) {
        mainPage.getChildren().clear();
        mainPage.getChildren().add(page);
        mainPage.toFront();
    }*/
}
