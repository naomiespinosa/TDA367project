package viewcontroller;

import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import model.Course;

/**
 * A Factory Method that loads and creates all the fxml/Anchorpanes that we need
 *
 * @author Hanna
 */
public class PageFactory {

  @Inject private GuiceFXMLLoader fxmlLoader;

  /**
   * Creates an AnchorPane for the belonging FXML
   *
   * @param parent parent
   * @return AnchorPane for the fxml
   * @throws IOException if we cant load the fxml
   */
  public AnchorPane createHomePage(MainPage parent) throws IOException {
    GuiceFXMLLoader.Result result =
        this.fxmlLoader.load(PageFactory.class.getClassLoader().getResource("fxml/HomePage.fxml"));
    AnchorPane root = result.getRoot();

    HomePage ctr = result.getController();
    ctr.setParent(parent);
    ctr.init();
    return root;
  }

  /**
   * Creates an AnchorPane for the belonging FXML
   *
   * @param parent needs to be connected to the parent
   * @return AnchorPane for the fxml
   * @throws IOException if we cant load the fxml
   */
  public AnchorPane createCourseSelectionPage(MainPage parent) throws IOException {
    GuiceFXMLLoader.Result result =
        this.fxmlLoader.load(
            PageFactory.class.getClassLoader().getResource("fxml/CourseSelectionPage.fxml"));

    AnchorPane root = result.getRoot();
    CourseSelectionPage ctr = result.getController();
    ctr.setParent(parent);
    return root;
  }

  /**
   * Creates an AnchorPane for the belonging FXML
   *
   * @return AnchorPane for the fxml
   * @throws IOException if we cant load the fxml
   */
  public AnchorPane createStatisticsPage() throws IOException {
    return this.fxmlLoader
        .load(PageFactory.class.getClassLoader().getResource("fxml/StatisticsPage.fxml"))
        .getRoot();
  }

  /**
   * Creates an AnchorPane for the belonging FXML
   *
   * @param course needs a course that will be displayed
   * @param parent needs a connection to the parent
   * @return AnchorPane for the fxml
   * @throws IOException if we cant load the fxml
   */
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

  /**
   * Creates an AnchorPane for the belonging FXML
   *
   * @param course The course to be displayed in the page
   * @param mainPage needs a connection to the mainpage
   * @return AnchorPane for the fxml
   * @throws IOException if we cant load the fxml
   */
  public AnchorPane createCourseMainPage(Course course, final MainPage mainPage)
      throws IOException {
    GuiceFXMLLoader.Result result =
        this.fxmlLoader.load(
            PageFactory.class.getClassLoader().getResource("fxml/CourseMainPage.fxml"));

    AnchorPane root = result.getRoot();

    CourseMainPage ctr = result.getController(); // Fetches the Controller for the fxml
    ctr.init(course);
    ctr.setParent(mainPage);

    return root;
  }

  /**
   * Creates an AnchorPane for the belonging FXML
   *
   * @return AnchorPane for the fxml
   * @throws IOException if we cant load the fxml
   */
  public AnchorPane createTimerPage() throws IOException {
    return this.fxmlLoader
        .load(PageFactory.class.getClassLoader().getResource("fxml/TimerPage.fxml"))
        .getRoot();
  }

  public AnchorPane createContactsPage() throws IOException {
    GuiceFXMLLoader.Result result =
        fxmlLoader.load(PageFactory.class.getClassLoader().getResource("fxml/ContactsPage.fxml"));

    AnchorPane root = result.getRoot();
    ContactsPage ctr = result.getController();
    ctr.init();
    return root;
  }
}
