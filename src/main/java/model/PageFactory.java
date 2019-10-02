package model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class PageFactory {
  public static AnchorPane createHomePage() {
    return null; // Don't have one yet
  }

  public static AnchorPane createCourseSelectionPage() throws IOException {
    return FXMLLoader.load(
        PageFactory.class.getClassLoader().getResource("fxml/CourseSelectionPage.fxml"));
  }

  public static AnchorPane createStatisticsPage() throws IOException {
    return FXMLLoader.load(
        PageFactory.class.getClassLoader().getResource("fxml/StatisticsPage.fxml"));
  }

  public static AnchorPane createCourseMainPage() throws IOException {
    return FXMLLoader.load(
        PageFactory.class.getClassLoader().getResource("fxml/CourseMainPage.fxml"));
  }

  public static AnchorPane createCoursePanelItem() throws IOException {
    return FXMLLoader.load(
            PageFactory.class.getClassLoader().getResource("fxml/CoursePanelItem.fxml"));
  }
}
