package views;

import javafx.fxml.FXML;

import javax.swing.*;
import javax.swing.text.html.ListView;
import java.awt.*;

public class CourseMainPage {
    @FXML private TextArea CourseName;

    //ToDoList
    @FXML private ListView toDoList;
    @FXML private Button newToDoButton;
    @FXML private TextArea newToDo;

    //Deadline
    @FXML private ListView deadlines;
    @FXML private Button newDeadlineButton;
    @FXML private TextArea newDeadline;

    //Activity
    @FXML private ListView activityList;

    //Timer in Course
    @FXML private Button courseStartTimerButton;
    @FXML private TextArea courseTimer;
}
