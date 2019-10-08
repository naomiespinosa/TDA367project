package controller;
import model.Course;
import model.User;
import model.min5aDataHandler;

import java.util.ArrayList;

// temporary mainController
public class min5aMainController {

    private min5aDataHandler dataHandler = min5aDataHandler.getInstance();
    private User user = User.getInstance();

   public ArrayList<Course> getCourseList(){
        return user.getCourses();
   }






}
