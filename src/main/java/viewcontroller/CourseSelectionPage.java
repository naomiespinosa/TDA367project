package viewcontroller;

import com.google.inject.Inject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TextField courseCodeTextArea;
    @FXML
    private Spinner yearSpinner;
    private int initialYear = 2019;
    @FXML
    private ToggleGroup periodToggleGroup;
    @FXML
    private RadioButton period1RadioButton;

    @FXML
    private RadioButton period2RadioButton;

    @FXML
    private RadioButton period3RadioButton;

    @FXML
    private RadioButton period4RadioButton;

    @Inject private User user; // temporary
    private MainPage parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initToggleGroup();
        resetSpinner();

    }



    void init(){
        // Temporary
        testClass();
        resetPage();
        updateLists();
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


    void updateLists(){
        sortCourses();
        try {
            showActiveCourses();
            showInactiveCourses();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void setShadow(AnchorPane courseItem) {   //Make the CourseListItems to have a shadow around them
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        courseItem.setEffect(dropShadow);
    }

    void sortCourses(){
        activeCourses.clear();
        inactiveCourses.clear();
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
    void closeTabButton(ActionEvent event){
        resetPage();
    }

    @FXML
    void closeTabMouse(MouseEvent event){
        resetPage();
    }

    @FXML
    void createNewCourse(ActionEvent event){
        String name = courseNameTextArea.getText();
        String code = courseCodeTextArea.getText();
        int year = (int) yearSpinner.getValue();
        int period = getPeriod();
        user.addCourse(name,code,year,period);

        updateLists();
        clearCourseInput();
        resetPage();
    }

    @FXML
    void deleteInputs(ActionEvent event){
        clearCourseInput();
    }

    private void clearCourseInput() {
        courseNameTextArea.clear();
        courseCodeTextArea.clear();
        period1RadioButton.setSelected(true);
        resetSpinner();
    }



    // Spinner
    void resetSpinner(){
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, initialYear, 1);
        yearSpinner.setValueFactory(valueFactory);
    }

    // Togglegroup

    private void initToggleGroup() {
        periodToggleGroup = new ToggleGroup();
        period1RadioButton.setToggleGroup(periodToggleGroup);
        period2RadioButton.setToggleGroup(periodToggleGroup);
        period3RadioButton.setToggleGroup(periodToggleGroup);
        period4RadioButton.setToggleGroup(periodToggleGroup);

        period1RadioButton.setSelected(true);
    }


    int getPeriod(){ // TODO
        if (periodToggleGroup.getSelectedToggle() != null){
            if (periodToggleGroup.getSelectedToggle()==period1RadioButton){
                return 1;
            } else if (periodToggleGroup.getSelectedToggle()==period2RadioButton) {
                return 2;
            } else if(periodToggleGroup.getSelectedToggle()==period3RadioButton){
                return 3;
            } else if (periodToggleGroup.getSelectedToggle()==period1RadioButton){
                return 4;
            }
        }
        return 0;
    }

    // Setters And Getters
    void setParent(MainPage parent) {
        this.parent = parent;
    }
}
