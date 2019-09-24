package controller;

import model.Course;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.List;

public class CourseOverviewPage {
    private List<Course> activeCourses;
    private List<Course> inactiveCourses;


    private void sortCourses(List<Course> courses) {   //Place active courses in one list & inactive courses in another
       int i;
        for(i = 0; i <= (courses.size()-1);i++) {
            if (courses.get(i).isActive()) {
                activeCourses.add(courses.get(i));
            }
            else if (!courses.get(i).isActive()){
                inactiveCourses.add(courses.get(i));

        }
    }

 }



    @FXML
    private TextField textFieldeActiveCourses;

    @FXML
    private TextField textFieldInActiveCourses;
}
