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
    private List<Course> activeCourses = new ArrayList<>();
    private List<Course> inactiveCourses = new ArrayList<>();

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
    }

    private void showInactiveCourses() {
    }



    public void sortCourses(){
        for (int i = 0; i < user.getCourses().size();i++){
            if (user.getCourse(i).isActive()){
                activeCourses.add(user.getCourse(i));
            }
            else{
                inactiveCourses.add(user.getCourse(i));
            }

    }




    }
    }


