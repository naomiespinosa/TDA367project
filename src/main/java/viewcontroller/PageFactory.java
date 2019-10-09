package viewcontroller;

import java.io.IOException;

import com.cathive.fx.guice.GuiceFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.Course;

import javax.swing.plaf.synth.SynthLookAndFeel;

public class PageFactory {
  public static AnchorPane createHomePage(MainPage parent) throws IOException {
    FXMLLoader homePageLoader =
        new FXMLLoader(PageFactory.class.getClassLoader().getResource("fxml/HomePage.fxml"));
    AnchorPane root = homePageLoader.load(); // Loads the FXML
    HomePage ctr = homePageLoader.getController(); // Fetches the Controller for the fxml
    ctr.setParent(parent);
    ctr.init();
    return root;
  }

  public static AnchorPane createCourseSelectionPage(MainPage parent) throws IOException {
    FXMLLoader courseSelectionPageLoader =
        new FXMLLoader(
            PageFactory.class.getClassLoader().getResource("fxml/CourseSelectionPage.fxml"));
    AnchorPane root = courseSelectionPageLoader.load(); // Loads the FXML
    CourseSelectionPage ctr =
        courseSelectionPageLoader.getController(); // Fetches the Controller for the fxml
    ctr.setParent(parent);
    ctr.init();
    return root;
  }

  public static AnchorPane createStatisticsPage() throws IOException {
    return FXMLLoader.load(
        PageFactory.class.getClassLoader().getResource("fxml/StatisticsPage.fxml"));
  }

  static AnchorPane createCoursePanelItem(Course course, MainPage parentController)
      throws IOException {
    FXMLLoader coursePanelLoader =
        new FXMLLoader(PageFactory.class.getClassLoader().getResource("fxml/CoursePanelItem.fxml"));
    AnchorPane root = coursePanelLoader.load(); // Loads the FXML
    CoursePanelItem ctr = coursePanelLoader.getController(); // Fetches the Controller for the fxml
    ctr.init(course, parentController);
    return root;
  }

  static AnchorPane createCourseMainPage(Course course, final MainPage mainPage)
      throws IOException {
    FXMLLoader courseMainPageLoader =
        new FXMLLoader(PageFactory.class.getClassLoader().getResource("fxml/CourseMainPage.fxml"));
    AnchorPane root = courseMainPageLoader.load(); // Loads the FXML
    CourseMainPage ctr =
        courseMainPageLoader.getController(); // Fetches the Controller for the fxml
    ctr.init(course);
    ctr.setParent(mainPage);

    return root;
  }

  public static AnchorPane createTimerPage(GuiceFXMLLoader fxmlLoader) throws IOException {
    return fxmlLoader.load(PageFactory.class.getClassLoader().getResource("fxml/TimerPage.fxml")).getRoot();
  }
}
