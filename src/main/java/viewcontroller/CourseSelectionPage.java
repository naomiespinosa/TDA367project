package viewcontroller;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;
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

    @FXML
    private FlowPane activeCoursesFlowpane;
    @FXML
    private FlowPane inactiveCoursesFlowpane;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane addCoursePane;
    @FXML
    private TextField courseNameTextArea;
    @FXML
    private TextField CourseCodeTextArea;
    @FXML
    private Spinner<?> semesterSpinner;
    @FXML
    private TextField yearTexArea;

    @Inject private User user; // temporary
    private MainPage parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // empty
    }

    void init(){
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

    void resetPage(){
        main.toFront();
        addCoursePane.toBack();
    }

    // List functionality

    // Method used to display all Courses
    private void showActiveCourses() throws IOException {
        activeCoursesFlowpane.getChildren().clear();

        for (Course course : activeCourses) { // Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(course, parent);
            setShadow(courseItem);
            activeCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    // Method that displays all inactive courses
    private void showInactiveCourses() throws IOException {
        inactiveCoursesFlowpane.getChildren().clear();

        for (Course course : inactiveCourses) {// Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(course, parent);
            setShadow(courseItem);
            inactiveCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    private void setShadow(AnchorPane courseItem) {
        DropShadow dropShadow = new DropShadow();

        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);

        courseItem.setEffect(dropShadow);
    }

    void sortCourses(){
        for (int i = 0; i < user.getCourses().size();i++){
            if (user.getCourse(i).isActive()){
                activeCourses.add(user.getCourse(i));
            }
            else{
                inactiveCourses.add(user.getCourse(i));
            }

        }
    }

    // Add course functionality


    @FXML
    void addCourse(ActionEvent event) {
        main.toBack();
        addCoursePane.toFront();
    }

    @FXML
    void closeTab(ActionEvent event){
        resetPage();
    }

    @FXML
    void closeTab(MouseEvent event){
        resetPage();
    }

    // Setters And Getters
    void setParent(MainPage parent) {
        this.parent = parent;
    }
}
