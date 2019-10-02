package controller;

import model.Course;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class CourseSelectionPageTest {

    @Test
    public void sortingLists(){
        CourseSelectionPage csp = new CourseSelectionPage();
        csp.init();
        User user = csp.getUser();
        List<Course> courses1 = csp.sortCourses(false);
        List<Course> courses2 = csp.sortCourses(true);
        //System.out.println(courses1.size());
        assertSame(1,courses1.size());
        assertSame(2,courses2.size());
    }
}
