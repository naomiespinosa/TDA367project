package model;

import java.io.IOException;

import controller.CourseMainPage;
import controller.CoursePanelItem;
import controller.CourseSelectionPage;
import controller.SidePanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class PageFactory {
  public static AnchorPane createHomePage() {
    return null; // Don't have one yet
  }

  public static AnchorPane createCourseSelectionPage(SidePanel parent) throws IOException {
    FXMLLoader courseSelectionPageLoader =
            new FXMLLoader(PageFactory.class.getClassLoader().getResource("fxml/CourseSelectionPage.fxml"));
    AnchorPane root = courseSelectionPageLoader.load(); // Loads the FXML
    CourseSelectionPage ctr = courseSelectionPageLoader.getController(); // Fetches the Controller for the fxml
    parent.setCourseSelectionController(ctr);
    return root;
  }

  public static AnchorPane createStatisticsPage() throws IOException {
    return FXMLLoader.load(
        PageFactory.class.getClassLoader().getResource("fxml/StatisticsPage.fxml"));
  }

  public static AnchorPane createCoursePanelItem(Course course, CourseSelectionPage parentController) throws IOException {
    FXMLLoader coursePanelLoader =
            new FXMLLoader(PageFactory.class.getClassLoader().getResource("fxml/CoursePanelItem.fxml"));
    AnchorPane root = coursePanelLoader.load(); // Loads the FXML
    CoursePanelItem ctr = coursePanelLoader.getController(); // Fetches the Controller for the fxml
    ctr.init(course, parentController);
    return root;
  }

  public static AnchorPane createCourseMainPage(Course course, CourseSelectionPage parentController) throws IOException {
    FXMLLoader courseMainPageLoader =
            new FXMLLoader(PageFactory.class.getClassLoader().getResource("fxml/CourseMainPage.fxml"));
    AnchorPane root = courseMainPageLoader.load(); // Loads the FXML for the SidePanel
    CourseMainPage ctr = courseMainPageLoader.getController(); // Fetches the Controller for the fxml
    ctr.init(course, parentController);
    return root;
  }
}
