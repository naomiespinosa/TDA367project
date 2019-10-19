package viewcontroller;

import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import model.Course;

public class PageFactory {

  @Inject private GuiceFXMLLoader fxmlLoader;

  public AnchorPane createHomePage(MainPage parent) throws IOException {
    GuiceFXMLLoader.Result result =
        this.fxmlLoader.load(PageFactory.class.getClassLoader().getResource("fxml/HomePage.fxml"));
    AnchorPane root = result.getRoot();

    HomePage ctr = result.getController();
    ctr.setParent(parent);
    ctr.init();
    return root;
  }

  public AnchorPane createCourseSelectionPage(MainPage parent) throws IOException {
    GuiceFXMLLoader.Result result =
        this.fxmlLoader.load(
            PageFactory.class.getClassLoader().getResource("fxml/CourseSelectionPage.fxml"));

    AnchorPane root = result.getRoot();
    CourseSelectionPage ctr = result.getController();
    ctr.setParent(parent);
    return root;
  }

  public AnchorPane createStatisticsPage() throws IOException {
    return this.fxmlLoader
        .load(PageFactory.class.getClassLoader().getResource("fxml/StatisticsPage.fxml"))
        .getRoot();
  }

  public AnchorPane createCoursePanelItem(final Course course, final MainPage parent)
      throws IOException {
    GuiceFXMLLoader.Result result =
        this.fxmlLoader.load(
            PageFactory.class.getClassLoader().getResource("fxml/CoursePanelItem.fxml"));

    AnchorPane root = result.getRoot();
    CoursePanelItem ctr = result.getController(); // Fetches the Controller for the fxml
    ctr.init(course, parent);
    return root;
  }

  public AnchorPane createCourseMainPage(Course course, final MainPage mainPage)
      throws IOException {
    GuiceFXMLLoader.Result result =
        fxmlLoader.load(PageFactory.class.getClassLoader().getResource("fxml/CourseMainPage.fxml"));

    AnchorPane root = result.getRoot();

    CourseMainPage ctr = result.getController(); // Fetches the Controller for the fxml
    ctr.init(course);
    ctr.setParent(mainPage);

    return root;
  }

  public AnchorPane createContactsPage() throws IOException {
    GuiceFXMLLoader.Result result =
        fxmlLoader.load(PageFactory.class.getClassLoader().getResource("fxml/ContactsPage.fxml"));

    AnchorPane root = result.getRoot();
    ContactsPage ctr = result.getController();
    ctr.init();
    return root;
  }

  public AnchorPane createTimerPage() throws IOException {
    return fxmlLoader
        .load(PageFactory.class.getClassLoader().getResource("fxml/TimerPage.fxml"))
        .getRoot();
  }
}
