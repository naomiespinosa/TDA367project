package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Course;
import model.PageFactory;
//import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.util.List;

public class CourseSelectionPage {
    private List<Course> activeCourses;
    private List<Course> inactiveCourses;
    private List<Course> allCourses;

    @FXML
    private FlowPane activeCoursesFlowpane;

    @FXML
    private FlowPane inactiveCoursesFlowpane;

    @FXML
    private AnchorPane smallCoursePanelItem;

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
       SidePanel coursePanelItemCtrl = coursePanelItemLoader.getController();
           setCoursePanelItem(PageFactory.createCoursePanelItem());
              // activeCoursesFlowpane.getChildren().add();

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
