package controller;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private AnchorPane coursePage;
    @FXML
    private AnchorPane main;


    @Inject private User user; // temporary

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Temporary
        testClass();
        resetPage();


        try {
            showActiveCourses();
            showInactiveCourses();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Only used now when we want to test our class
    private void testClass(){
        user = new User();
        user.addCourse("prog1", "TDA333", 1, 2);
        user.addCourse("prog2", "TDA333", 1, 2);
        user.addCourse("prog3", "TDA333", 1, 2);
        user.getCourse(2).endCourse();
        user.addCourse("prog4", "TDA333", 1, 2);
        user.addCourse("prog4", "TDA333", 1, 2);
    }

    // Method used to display all Courses
    private void showActiveCourses() throws IOException {
        activeCourses = getActiveCourses(); // might not need this method. unless we want to update them separately
        activeCoursesFlowpane.getChildren().clear();

        for (int i = 0; i < activeCourses.size(); i++){ // Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(activeCourses.get(i),this);
            activeCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    // Method that displays all inactive courses
    private void showInactiveCourses() throws IOException {
        inactiveCourses = getInactiveCourses();
        inactiveCoursesFlowpane.getChildren().clear();

        for (int i = 0; i < inactiveCourses.size(); i++){// Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(inactiveCourses.get(i),this);
            inactiveCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    // the three methods below will be needed to change to look more neat
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

    void pressed(Course course) throws IOException {
        coursePage.getChildren().clear();
        coursePage.getChildren().add(PageFactory.createCourseMainPage(course,this));
        coursePage.toFront();
        main.toBack();
    }

    public void resetPage(){
        main.toFront();
        coursePage.getChildren().clear();
        coursePage.toBack();
    }

}
