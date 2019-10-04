package viewcontroller;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class PanelItemManager {

    private static User user = User.getInstance();

    // Method used to display all Courses
    static void showActiveCourses(FlowPane activeCoursesFlowpane, MainPage parent) throws IOException {
        activeCoursesFlowpane.getChildren().clear();
        for (Course course : sort(true)) { // Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(course, parent);
            setShadow(courseItem);
            activeCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    // Method that displays all inactive courses
    static void showInactiveCourses(FlowPane inactiveCoursesFlowpane, MainPage parent) throws IOException {
        inactiveCoursesFlowpane.getChildren().clear();

        for (Course course : sort(false)) {// Runs through all the courses to only show the correct ones
            AnchorPane courseItem = PageFactory.createCoursePanelItem(course, parent);
            setShadow(courseItem);
            inactiveCoursesFlowpane.getChildren().add(courseItem);
        }
    }

    private static List<Course> sort(Boolean status){
        List<Course> tempCourses = new ArrayList<>();
        for (int i = 0; i < user.getCourses().size();i++) {
            if (user.getCourse(i).isActive() == status) {
                tempCourses.add(user.getCourse(i));
            }
        }
        return tempCourses;
    }

    private static void setShadow(AnchorPane courseItem) {   //Make the CourseListItems to have a shadow around them
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        courseItem.setEffect(dropShadow);
    }

}
