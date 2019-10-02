package controller;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
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
    private List<Course> activeCourses = new ArrayList<>();
    private List<Course> inactiveCourses = new ArrayList<>();
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
        sortCourses();

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
        user.addCourse("Funktionell Programmering", "TDA333", 1, 2);
        user.addCourse("Programmering", "TDA333", 1, 2);
        user.addCourse("Mattematisk Analys", "TDA333", 1, 2);
        user.getCourse(2).endCourse();
        user.addCourse("Kommunikation och ingejörskunskap", "TDA333", 1, 2);
        user.addCourse("Hej", "TDA333", 1, 2);
    }

    // Method used to display all Courses
    private void showActiveCourses() throws IOException {
        activeCoursesFlowpane.getChildren().clear();

        for (int i = 0; i < activeCourses.size(); i++){ // Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(activeCourses.get(i),this);
            setShadow(courseItem);
            activeCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    private void setShadow(AnchorPane courseItem) {
        DropShadow dropShadow = new DropShadow();

        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);

        courseItem.setEffect(dropShadow);
    }

    // Method that displays all inactive courses
    private void showInactiveCourses() throws IOException {
        inactiveCoursesFlowpane.getChildren().clear();

        for (int i = 0; i < inactiveCourses.size(); i++){// Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(inactiveCourses.get(i),this);
            setShadow(courseItem);
            inactiveCoursesFlowpane.getChildren().add(courseItem);
        }
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

    public void sortCourses(){
        for (int i = 0; i < user.getCourses().size();i++){
            if (user.getCourse(i).isActive()){
                activeCourses.add(user.getCourse(i));
            }
            else{
                inactiveCourses.add(user.getCourse(i));
            }

}}}
