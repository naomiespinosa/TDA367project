package viewcontroller;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import model.Course;

import java.io.IOException;

public class HomePage extends Observer{
    private MainPage parent;

  @FXML private FlowPane activeCoursesFlowpane;
    @FXML
    private FlowPane activeCoursesFlowpane;

    void init(){
        updateLists();
        CourseManager.attach(this);
    }

    private void updateLists(){
        try {
            PanelItemManager.showActiveCourses(activeCoursesFlowpane, parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setParent(MainPage parent) {
        this.parent=parent;
    }

    @Override
    public void update() {
        updateLists();
    }
}
