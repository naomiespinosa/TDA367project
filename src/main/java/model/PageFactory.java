package model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class PageFactory {
  public static AnchorPane createHomePage() {
    return null; // Don't have one
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
}
