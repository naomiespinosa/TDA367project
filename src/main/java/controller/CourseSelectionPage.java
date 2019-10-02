package controller;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Course;
import model.PageFactory;
import model.User;
//import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CourseSelectionPage implements Initializable{
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

    @Inject private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Temporary
        user = new User();
        user.addCourse("prog", "TDA333", 1, 2);
        // TODO show active
        showActiveCourses();
        // TODO show inactive
        showInactiveCourses();
    }

    // only used for the test
    public void init(){
        // Temporary
        user = new User();
        user.addCourse("prog", "TDA333", 1, 2);
        user.addCourse("prog", "TDA333", 1, 2);
        user.addCourse("prog", "TDA333", 1, 2);
        user.getCourse(0).endCourse();
        // TODO show active
        showActiveCourses();
        // TODO show inactive
        showInactiveCourses();
    }

    public User getUser(){
        return user;
    }

    private void showActiveCourses() {
        activeCourses = getActiveCourses();
    }

    private void showInactiveCourses() {
        inactiveCourses = getInactiveCourses();

    }

    private List<Course> getActiveCourses() {
        return sortCourses(true);
    }

    private List<Course> getInactiveCourses(){
        return sortCourses(false);
    }

    public List<Course> sortCourses(Boolean status){
        List<Course> tempCourses = new ArrayList<>();
        for (int i = 0; i < user.getCourses().size();i++){
            if (user.getCourse(i).isActive() == status){
                tempCourses.add(user.getCourse(i));
            }
        }
        return tempCourses;
    }



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
